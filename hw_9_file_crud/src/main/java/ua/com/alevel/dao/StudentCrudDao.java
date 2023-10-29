package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;

import java.util.Collection;

public interface StudentCrudDao<BE extends Student> {
    void create(BE be);
    void update(long id, BE be);
    void delete(Long id);
    boolean existsById(Long id);
    BE findById(long id);
    Collection findAll();
}
