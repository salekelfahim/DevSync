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

public class RequestServlet extends HttpServlet {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DevSyncPU");
    RequestRepository requestRepository = new RequestRepositoryImpl(entityManagerFactory);
    private final RequestService requestService = new RequestService(requestRepository);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "list":
                listRequests(request, response);
                break;
            case "approve":
                approveRequest(request, response);
                break;
            case "reject":
                rejectRequest(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    private void listRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Request> requests = requestService.getAllRequests();
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/WEB-INF/views/requests.jsp").forward(request, response);
    }


    private void approveRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void rejectRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}