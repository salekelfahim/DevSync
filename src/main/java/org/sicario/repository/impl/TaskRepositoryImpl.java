package org.sicario.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.sicario.model.entities.Task;
import org.sicario.model.entities.User;
import org.sicario.model.enums.TaskStatus;
import org.sicario.repository.interfaces.TaskRepository;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryImpl implements TaskRepository {
    EntityManagerFactory entityManagerFactory;

    public TaskRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }finally {
            entityManager.close();
        }
    }
    @Override
    public Optional<Task> findById(long id) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Task task = entityManager.find(Task.class, id);
            return Optional.ofNullable(task);
        }
    }
    @Override
    public List<Task> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT t FROM Task t", Task.class)
                    .getResultList();
        }
    }
    @Override
    public void delete(Task task) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Task managedTask = entityManager.merge(task);
            entityManager.remove(managedTask);
            entityManager.getTransaction().commit();
        }
    }
    @Override
    public void update(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try  {
            entityManager.getTransaction().begin();
            entityManager.merge(task);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
    @Override
    public List<Task> findByStatusAndUser(TaskStatus status, User user) {
        TypedQuery<Task> query = entityManagerFactory.createEntityManager().createQuery("SELECT t FROM Task t WHERE t.status = :status AND t.assignee = :user", Task.class);
        query.setParameter("status", status);
        query.setParameter("user", user);
        return query.getResultList();
    }
    @Override
    public List<Task> findByUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.assignee = :user", Task.class);
            query.setParameter("user", user);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
