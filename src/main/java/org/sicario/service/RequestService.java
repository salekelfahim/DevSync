package org.sicario.service;

import org.sicario.model.entities.Request;
import org.sicario.repository.interfaces.RequestRepository;


import java.util.List;
import java.util.Optional;


public class RequestService {

    RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request createRequest(Request request) {
        if (request == null || request.getUser() == null || request.getTask() == null) {
            throw new IllegalArgumentException("Request, user, or task cannot be null.");
        }
        return requestRepository.save(request);
    }

    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request> getRequestsByUserId(Long userId) {
        return requestRepository.findByUserId(userId);
    }

    public List<Request> getRequestsByTaskId(Long taskId) {
        return requestRepository.findByTaskId(taskId);
    }

    public List<Request> getRequestsByStatus(String status) {
        return requestRepository.findByStatus(status);
    }


    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    public Optional<Request> approveRequest(Long requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            if ("PENDING".equals(request.getStatus())) {
                request.setStatus("APPROVED");
                return Optional.of(requestRepository.update(request));
            }
        }
        return Optional.empty();
    }

    public boolean rejectRequest(Long requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            if ("PENDING".equals(request.getStatus())) {
                request.setStatus("REJECTED");
                requestRepository.update(request);
                return true;
            }
        }
        return false;
    }
}
