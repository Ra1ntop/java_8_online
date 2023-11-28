package ua.com.alevel.factory;

import lombok.Getter;
import ua.com.alevel.util.ResourceUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;


@Getter
public class JdbcFactory {

    private Connection connection;
    private Statement statement;

    @Getter
    private static final JdbcFactory instance = new JdbcFactory();

    private JdbcFactory() {}

    public void initDB(Class<?> mainClass) {
        Map<String, String> map = ResourceUtil.getResources(mainClass.getClassLoader());
        try {
            Class.forName(map.get("jdbc.driver"));
            connection = DriverManager.getConnection(
                    map.get("jdbc.url"),
                    map.get("jdbc.user"),
                    map.get("jdbc.password")
            );
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
