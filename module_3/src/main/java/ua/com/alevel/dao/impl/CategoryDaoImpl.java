package ua.com.alevel.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.com.alevel.dao.CategoriesDao;
import ua.com.alevel.entity.Categories;

public class CategoryDaoImpl implements CategoriesDao {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void create(Categories category) {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        System.out.println("commit");
        entityManager.getTransaction().commit();
    }

    @Override
    public Categories findById(Long id) {

        return entityManager.find(Categories.class, id);
    }
}
