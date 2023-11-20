package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.data.PaginationData;
import ua.com.alevel.entity.Student;
import ua.com.alevel.factory.JdbcFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    private final JdbcFactory jdbcFactory = JdbcFactory.getInstance();

    private static final String CREATE_EMPLOYEE = "insert into students values (default, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "update students set first_name = ?, last_name = ?, age = ? where id = ?";
    private static final String DELETE_EMPLOYEE = "delete from students where id = ?";
    private static final String CHEK_EXIST_STUDENT = "select count(id) as count_of_students from students where id = ?";

    private static final String FIND_EMPLOYEE = "select * from employees where id = ";
    private static final String FIND_ALL_EMPLOYEE = "select * from students order by ? limit ?, ?";
    private static final String FIND_ALL_EMPLOYEE_BY_DEPARTMENT = "select id, first_name, last_name, age from students as e left join gro_stu as de on e.id = de.emp_id where de.dep_id = ?";

    private static final String DELETE_DEPENDENCY = "DELETE FROM gro_stu WHERE emp_id = ?";

    @Override
    public void create(Student student) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(CREATE_EMPLOYEE)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setLong(3, student.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(UPDATE_EMPLOYEE)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.setLong(4, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_DEPENDENCY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_EMPLOYEE)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit(Long id) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(CHEK_EXIST_STUDENT)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            long count = rs.getLong("count_of_students");
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Student> findById(Student student) {
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll(PaginationData data) {
        StringBuilder order = new StringBuilder();
        order.append(data.getSort());
        order.append(" ");
        order.append(data.getOrderBy());
        int start = (data.getPage() - 1) * data.getSize();
        Collection<Student> employees = new ArrayList<>();
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(FIND_ALL_EMPLOYEE)) {
            ps.setString(1, order.toString());
            ps.setInt(2, start);
            ps.setInt(3, data.getSize());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employees.add(generateEmployeeByResultSet(rs));
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Collection<Student> findByDepartment(Long dep_id) {
        Collection<Student> employees = new ArrayList<>();
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(FIND_ALL_EMPLOYEE_BY_DEPARTMENT)) {
            ps.setLong(1, dep_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employees.add(generateEmployeeByResultSet(rs));
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private Student generateEmployeeByResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        Long id = rs.getLong("id");
        String fn = rs.getString("first_name");
        String ln = rs.getString("last_name");
        int age = rs.getInt("age");
        student.setId(id);
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        return student;
    }
}
