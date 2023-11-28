package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CategoriesDao;
import ua.com.alevel.dao.impl.CategoryDaoImpl;
import ua.com.alevel.entity.Categories;
import ua.com.alevel.service.CategoriesService;

public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesDao categoriesDao = new CategoryDaoImpl();

    @Override
    public void create(Categories categories) {

        categoriesDao.create(categories);
    }

    @Override
    public Categories findById(Long id) {

        return categoriesDao.findById(id);
    }
}
