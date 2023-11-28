package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

public interface BaseService <BE extends BaseEntity> {
    void create(BE be);
}
