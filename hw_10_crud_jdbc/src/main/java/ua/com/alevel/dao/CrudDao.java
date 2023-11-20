package ua.com.alevel.dao;

import ua.com.alevel.data.PaginationData;
import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface CrudDao <BE extends BaseEntity>{
    void create(BE be);
    void update(BE be);
    void delete(Long id);
    boolean isExit(Long id);
    Optional<BE> findById(BE be);
    Collection<BE> findAll(PaginationData data);


}
