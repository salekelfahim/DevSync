package org.sicario.servlets;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sicario.model.entities.Request;
import org.sicario.repository.impl.RequestRepositoryImpl;
import org.sicario.repository.impl.UserRepositoryImpl;
import org.sicario.repository.interfaces.RequestRepository;
import org.sicario.repository.interfaces.UserRepository;
import org.sicario.service.RequestService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class RequestServlet extends HttpServlet {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DevSyncPU");
    RequestRepository requestRepository = new RequestRepositoryImpl(entityManagerFactory);
    private final RequestService requestService = new RequestService(requestRepository);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("list".equals(action)) {
            listRequests(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("approve".equals(action)) {
            approveRequest(request, response);
        } else if ("reject".equals(action)) {
            rejectRequest(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    private void listRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Request> requests = requestService.getAllRequests();
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/WEB-INF/views/requests.jsp").forward(request, response);
    }


    private void approveRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestId = Long.parseLong(request.getParameter("requestId"));
        Optional<Request> approvedRequest = requestService.approveRequest(requestId);

        if (approvedRequest.isPresent()) {
            Long taskId = approvedRequest.get().getTask().getId();
            response.sendRedirect(request.getContextPath() + "/tasks?action=edit&taskId=" + taskId);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Request not found or not in PENDING state");
        }
    }


    private void rejectRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestId = Long.parseLong(request.getParameter("requestId"));
        boolean rejected = requestService.rejectRequest(requestId);
        if (rejected) {
            response.sendRedirect(request.getContextPath() + "/requests?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Request not found or not in PENDING state");
        }
    }
}