package ua.com.alevel.dao.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.com.alevel.dao.StudentCrudDao;
import ua.com.alevel.entity.Student;

import java.util.Collection;
import java.util.Optional;

public class StudentCrudDaoImpl implements StudentCrudDao {

    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("jpa-hibernate-mysql");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void create(Student entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Student entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        if (findById(id).isPresent()) {
            entityManager.remove(findById(id).get());
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Student> findAll() {
        return entityManager.createQuery("select s from Student s").getResultList();

    }

}
