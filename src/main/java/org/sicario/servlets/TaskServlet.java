package org.sicario.servlets;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sicario.model.entities.Tag;
import org.sicario.model.entities.Task;
import org.sicario.model.entities.User;
import org.sicario.model.enums.TaskStatus;
import org.sicario.model.enums.UserRole;
import org.sicario.repository.impl.TagRepositoryImpl;
import org.sicario.repository.impl.TaskRepositoryImpl;
import org.sicario.repository.impl.UserRepositoryImpl;
import org.sicario.repository.interfaces.TagRepository;
import org.sicario.repository.interfaces.TaskRepository;
import org.sicario.repository.interfaces.UserRepository;
import org.sicario.service.TagService;
import org.sicario.service.TaskService;
import org.sicario.service.UserService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskServlet extends HttpServlet {

    TaskService taskService;
    TagService tagService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DevSyncPU");
        TaskRepository taskRepository = new TaskRepositoryImpl(entityManagerFactory);
        TagRepository tagRepository = new TagRepositoryImpl(entityManagerFactory);
        UserRepository userRepository = new UserRepositoryImpl(entityManagerFactory);
        taskService = new TaskService(taskRepository);
        tagService = new TagService(tagRepository);
        userService = new UserService(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("list".equals(action)) {
            tasks(request, response);
        } else if ("create".equals(action)) {
            showCreateForm(request, response);
        } else if ("userTasks".equals(action)) {
            userTasks(request, response);
        } else if ("changeStatus".equals(action)) {
            changeStatus(request, response);
        } else {
            tasks(request, response);
        }
    }

    private void tasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> tasks = taskService.findAll();
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/WEB-INF/views/tasks.jsp").forward(request, response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tag> tags = tagService.findAll();
        List<User> users = userService.getRegularUsers();
        request.setAttribute("tags", tags);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/createTaskForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTask(request, response);
        }else if ("refuseTask".equals(action)) {
            refuseTask(request, response);
        }
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User creator = (User) request.getSession().getAttribute("loggedUser");
        if (creator == null) {
            response.sendRedirect("tasks?action=list");
            return;
        }
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate creationDate = LocalDate.parse(request.getParameter("creationDate"));
        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));
        String[] tagIds = request.getParameterValues("tags[]");
        String assigneeId = request.getParameter("assignee_id");

        String validationError = validateTaskForm(title, creationDate, dueDate, tagIds, description);

        if (validationError != null) {

            request.setAttribute("errorMessage", validationError);
            request.getRequestDispatcher("tasks?action=create").forward(request, response);

        } else{
            Task task = new Task(title,description,creationDate,dueDate, TaskStatus.NOT_STARTED, null,creator);
            List<Tag> selectedTags = new ArrayList<>();
            if (tagIds != null) {
                for (String tagId : tagIds) {
                    Optional<Tag> tag = tagService.findById(Long.valueOf(tagId));
                    tag.ifPresent(selectedTags::add);
                }
            }
            task.setTags(selectedTags);

            if (assigneeId != null && !assigneeId.isEmpty()) {
                User assignee = userService.getUserById(Long.valueOf(assigneeId));
                task.setAssignee(assignee);
            } else {
                task.setAssignee(null);
            }
            taskService.create(task);

            if (creator.getRole() == UserRole.MANAGER) {
                response.sendRedirect("tasks?action=list");
            } else {
                response.sendRedirect("tasks?action=userTasks");
            }
        }
    }

    private String validateTaskForm(String title, LocalDate creationDate, LocalDate dueDate, String[] tags, String description) {
        if (title == null || title.trim().isEmpty()) {
            return "Title is required.";
        }

        LocalDate today = LocalDate.now();
        LocalDate threeDaysAhead = today.plusDays(3);

        if (creationDate.isBefore(threeDaysAhead)) {
            return "Creation date must be at least 3 days ahead.";
        }

        if (dueDate.isBefore(creationDate)) {
            return "Due date cannot be earlier than creation date.";
        }

        if (tags == null || tags.length == 0) {
            return "At least one tag is required.";
        }

        if (description == null || description.trim().isEmpty()) {
            return "Description is required.";
        }

        return null;
    }

    private void userTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("loggedUser");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/users?action=login");
            return;
        }

        List<Task> allTasks = taskService.findByUser(user);
        LocalDate today = LocalDate.now();

        for (Task task : allTasks) {
            if (task.getDueDate().isBefore(today) && task.getStatus() != TaskStatus.COMPLETED) {
                task.setStatus(TaskStatus.CANCELED);
                taskService.update(task);
            }
        }

        List<Task> notStartedTasks = taskService.findByStatusAndUser(TaskStatus.NOT_STARTED, user);
        List<Task> inProgressTasks = taskService.findByStatusAndUser(TaskStatus.IN_PROGRESS, user);
        List<Task> completedTasks = taskService.findByStatusAndUser(TaskStatus.COMPLETED, user);
        List<Task> canceledTasks = taskService.findByStatusAndUser(TaskStatus.CANCELED, user);

        request.setAttribute("notStartedTasks", notStartedTasks);
        request.setAttribute("inProgressTasks", inProgressTasks);
        request.setAttribute("completedTasks", completedTasks);
        request.setAttribute("canceledTasks", canceledTasks);

        request.getRequestDispatcher("/WEB-INF/views/userTasks.jsp").forward(request, response);
    }

    private void changeStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long taskId = Long.parseLong(request.getParameter("taskId"));
        String newStatus = request.getParameter("newStatus");

        Task task = taskService.findById(taskId).orElse(null);
        if (task != null) {
            LocalDate today = LocalDate.now();
            if (task.getDueDate().isBefore(today)) {
                task.setStatus(TaskStatus.CANCELED);
            } else {
                task.setStatus(TaskStatus.valueOf(newStatus));
            }
            taskService.update(task);
        }

        response.sendRedirect(request.getContextPath() + "/tasks?action=userTasks");
    }

    private void refuseTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId = request.getParameter("taskId");
        Optional<Task> optionalTask = taskService.findById(Long.parseLong(taskId));

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            User manager = userService.getUserById(1L);
            User user = (User) request.getSession().getAttribute("loggedUser");
            if (manager != null) {
                if (user.getTokenRefuse() > 0) {
                task.setAssignee(manager);
                user.setTokenRefuse(user.getTokenRefuse() - 1);
                userService.updateUser(user);
                taskService.update(task);
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/tasks?action=userTasks");
    }

}
