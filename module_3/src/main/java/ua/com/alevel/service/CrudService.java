package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;
import java.util.Collection;

public interface CrudService<E extends BaseEntity> {

    void create(E e);
    void update(E e);
    void delete(E e);
    E findById(Long id);
    Collection<E> findAll();
}
