package ua.com.alevel.dao;

import java.util.Collection;
import java.util.Optional;

public interface CrudDao {
    void create();
    void update();
    void delete(Long id);
    boolean existsById(Long id);
    Collection findAll();
}
