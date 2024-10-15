package org.sicario.repository.interfaces;

import org.sicario.model.entities.Request;

import java.util.List;
import java.util.Optional;

public interface RequestRepository {
    Request save(Request request);
    Optional<Request> findById(Long id);
    List<Request> findAll();
    List<Request> findByUserId(Long userId);
    List<Request> findByTaskId(Long taskId);
    List<Request> findByStatus(String status);
    void deleteById(Long id);
}
