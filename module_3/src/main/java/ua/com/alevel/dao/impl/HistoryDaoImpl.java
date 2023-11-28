package ua.com.alevel.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.com.alevel.dao.HistoryDao;
import ua.com.alevel.entity.History;

public class HistoryDaoImpl implements HistoryDao {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void create(History history) {
        entityManager.getTransaction().begin();
        entityManager.persist(history);
        entityManager.getTransaction().commit();
    }
}
