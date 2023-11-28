package ua.com.alevel.dao;


import ua.com.alevel.entity.Categories;

public interface CategoriesDao extends BaseDao<Categories> {

    Categories findById(Long id);
}
