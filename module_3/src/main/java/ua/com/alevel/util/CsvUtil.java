package ua.com.alevel.util;

import au.com.bytecode.opencsv.CSVWriter;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Categories;
import ua.com.alevel.entity.History;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.factory.JdbcFactory;
import ua.com.alevel.entity.Categories;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvUtil {

    private static final JdbcFactory jdbcFactory = JdbcFactory.getInstance();

    public static void getHistory(Long id) {
        writeCsv(findByAccountId(id));
    }

    private static Collection<History> findByAccountId(Long id) {
        Collection<History> histories = new ArrayList<>();
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement("select * from histories where account_id = " + id)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                History history = new History();
                history.setId(rs.getLong("id"));
                history.setCategory(findCategoryById(rs.getLong("category_id")));
                history.setOperation(findOperationById(rs.getLong("operation_id")));
                histories.add(history);
            }
            return histories;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void writeCsv(Collection<History> histories) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("history.csv"))) {
            List<String[]> list = new ArrayList<>();
            for (History history : histories) {
                String[] strings = new String[5];
                strings[0] = history.getOperation().getDateTime();
                strings[1] = history.getCategory().getName();
                Account account1 = history.getOperation().getAccount1();
                Account account2 = history.getOperation().getAccount2();
                if (account1 != null) {
                    strings[2] = String.valueOf(account1.getId());
                } else {
                    strings[2] = "deleted";
                }
                if (account2 != null) {
                    strings[3] = String.valueOf(account2.getId());
                } else {
                    strings[3] = "deleted";
                }
                strings[4] = String.valueOf(history.getOperation().getSum());
                list.add(strings);
            }
            csvWriter.writeAll(list);
            csvWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Account findAccountById(Long id) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement("select * from accounts where id = " + id)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    private static Operation findOperationById(Long id) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement("select * from operations where id = " + id)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Operation operation = new Operation();
                operation.setDateTime(rs.getString("date_time"));
                operation.setAccount1(findAccountById(rs.getLong("from_account_id")));
                operation.setAccount2(findAccountById(rs.getLong("to_account_id")));
                operation.setSum(rs.getLong("amount"));
                return operation;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    private static Categories findCategoryById(Long id) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement("select * from categories where id = " + id)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categories category = new Categories();
                category.setName(rs.getString("name"));
                return category;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
