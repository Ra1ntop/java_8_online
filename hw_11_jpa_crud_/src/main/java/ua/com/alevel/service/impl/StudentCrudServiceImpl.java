package ua.com.alevel.service.impl;


import ua.com.alevel.dao.StudentCrudDao;
import ua.com.alevel.dao.impl.StudentCrudDaoImpl;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentCrudService;

import java.util.Collection;
import java.util.Optional;

public class StudentCrudServiceImpl implements StudentCrudService {


    private final StudentCrudDao studentCrudDao = new StudentCrudDaoImpl();

    @Override
    public void create(Student entity) {
        studentCrudDao.create(entity);
    }

    @Override
    public void update(Student entity) {
        Optional<Student> optionalStudent = studentCrudDao.findById(entity.getId());
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        studentCrudDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<Student> optionalStudent = studentCrudDao.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        studentCrudDao.delete(id);
    }

    @Override
    public Student findById(Long id) {
        Optional<Student> optionalStudent = studentCrudDao.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        return optionalStudent.get();
    }

    @Override
    public Collection<Student> findAll() {
        return studentCrudDao.findAll();
    }
}
