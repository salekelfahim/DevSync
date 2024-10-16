package org.sicario.repository.impl;

import jakarta.persistence.*;
import org.sicario.model.entities.Request;
import org.sicario.repository.interfaces.RequestRepository;

import java.util.List;
import java.util.Optional;

public class RequestRepositoryImpl implements RequestRepository {

    private final EntityManagerFactory entityManagerFactory;

    public RequestRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Request save(Request request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (request.getId() == null) {
                entityManager.persist(request);
            } else {
                entityManager.merge(request);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenceException("Error saving request: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return request;
    }

    @Override
    public Optional<Request> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Request request = entityManager.find(Request.class, id);
            return Optional.ofNullable(request);
        }
    }

    @Override
    public List<Request> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Request> query = entityManager.createQuery("SELECT r FROM Request r", Request.class);
            return query.getResultList();
        }
    }

    @Override
    public List<Request> findByUserId(Long userId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Request> query = entityManager.createQuery(
                    "SELECT r FROM Request r WHERE r.user.id = :userId", Request.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        }
    }

    @Override
    public List<Request> findByTaskId(Long taskId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Request> query = entityManager.createQuery(
                    "SELECT r FROM Request r WHERE r.task.id = :taskId", Request.class);
            query.setParameter("taskId", taskId);
            return query.getResultList();
        }
    }

    @Override
    public List<Request> findByStatus(String status) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Request> query = entityManager.createQuery(
                    "SELECT r FROM Request r WHERE r.status = :status", Request.class);
            query.setParameter("status", status);
            return query.getResultList();
        }
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Request request = entityManager.find(Request.class, id);
            if (request != null) {
                entityManager.remove(request);
            }
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
    public Request update(Request request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getId() == null) {
            throw new IllegalArgumentException("Cannot update a request without an ID");
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Request updatedRequest = entityManager.merge(request);
            entityManager.getTransaction().commit();
            return updatedRequest;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenceException("Error updating request: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

}
