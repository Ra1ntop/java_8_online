package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;

import java.util.Collection;

public interface StudentDao extends CrudDao<Student> {
    Collection<Student> findByDepartment(Long dep_id);
}
