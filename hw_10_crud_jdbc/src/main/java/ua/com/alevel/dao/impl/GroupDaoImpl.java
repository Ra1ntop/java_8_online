package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.data.PaginationData;
import ua.com.alevel.entity.Group;
import ua.com.alevel.factory.JdbcFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class GroupDaoImpl implements GroupDao {
    private final JdbcFactory jdbcFactory = JdbcFactory.getInstance();
    private static final String CREATE_GROUP = "insert into groupsOfStudents values (default, ?)";
    private static final String ATTACH_EMPLOYEE_TO_DEPARTMENT = "insert into gro_stu values (?, ?)";
    private static final String CHEK_EXIST_STUDENT = "select count(id) as count_of_groups from groupsOfStudents where id = ?";
    private static final String DELETE_GROUP = "delete from groupsOfStudents where id = ?";
    private static final String DELETE_DEPENDENCY = "DELETE FROM gro_stu WHERE dep_id = ?";


    @Override
    public void create(Group group) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(CREATE_GROUP)) {
            ps.setString(1, group.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Group group) {

    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_DEPENDENCY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_GROUP)) {
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
            long count = rs.getLong("count_of_groups");
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Group> findById(Group group) {
        return Optional.empty();
    }

    @Override
    public Collection<Group> findAll(PaginationData data) {
        return null;
    }

    @Override
    public void addStudentsToGroup(Long dep_id, Long stud_id) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(ATTACH_EMPLOYEE_TO_DEPARTMENT)) {
            ps.setLong(1, dep_id);
            ps.setLong(2, stud_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
