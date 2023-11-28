package ua.com.alevel.service;


import ua.com.alevel.entity.Categories;

public interface CategoriesService extends BaseService<Categories> {

    Categories findById(Long id);
}
