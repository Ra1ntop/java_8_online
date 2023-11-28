package ua.com.alevel.dao;


import ua.com.alevel.entity.BaseEntity;

public interface BaseDao<BE extends BaseEntity> {

    void create(BE e);
}
