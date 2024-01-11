package ua.com.alevel.service.impl;

import ua.com.alevel.dao.GroupCrudDao;
import ua.com.alevel.dao.StudentCrudDao;
import ua.com.alevel.dao.impl.GroupCrudDaoImpl;
import ua.com.alevel.dao.impl.StudentCrudDaoImpl;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupCrudService;

import java.util.Collection;
import java.util.Optional;

public class GroupCrudServiceImpl implements GroupCrudService {

    private final GroupCrudDao groupCrudDao = new GroupCrudDaoImpl();
    private final StudentCrudDao studentCrudDao = new StudentCrudDaoImpl();

    @Override
    public void create(Group entity) {
        groupCrudDao.create(entity);
    }

    @Override
    public void update(Group entity) {
        Optional<Group> optionalGroup = groupCrudDao.findById(entity.getId());
        if (optionalGroup.isEmpty()) {
            throw new RuntimeException("Group not found");
        }
        groupCrudDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<Group> optionalGroup = groupCrudDao.findById(id);
        if (optionalGroup.isEmpty()) {
            throw new RuntimeException("Group not found");
        }
        groupCrudDao.delete(id);
    }

    @Override
    public Group findById(Long id) {
        Optional<Group> optionalGroup = groupCrudDao.findById(id);
        if (optionalGroup.isEmpty()) {
            throw new RuntimeException("Group not found");
        }
        return optionalGroup.get();
    }

    @Override
    public Collection<Group> findAll() {
        return groupCrudDao.findAll();
    }


    @Override
    public void addStudentToGroup(Long groupId, Long studentId) {
        Optional<Student> optionalStudent = studentCrudDao.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        Group group = findById(groupId);
        group.getStudents().add(optionalStudent.get());
        groupCrudDao.update(group);
    }

    @Override
    public void deleteStudentFromGroup(Long groupId, Long studentId) {
        Optional<Student> optionalStudent = studentCrudDao.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        Group group = findById(groupId);
        group.getStudents().remove(optionalStudent.get());
        groupCrudDao.update(group);
    }
}
