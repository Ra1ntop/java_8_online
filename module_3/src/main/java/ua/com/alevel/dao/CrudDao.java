package ua.com.alevel.dao;


import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;

public interface CrudDao<BE extends BaseEntity> {

    void create(BE Be);
    void update(BE Be);
    void delete(BE Be);
    BE findById(Long id);
    Collection<BE> findAll();
}
