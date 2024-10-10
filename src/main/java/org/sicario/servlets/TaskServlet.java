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
            listTasks(request, response);
        } else if ("create".equals(action)) {
            showCreateForm(request, response);
        }else if ("edit".equals(action)) {
//            showEditForm(request, response);
        } else if ("delete".equals(action)) {
//            deleteTask(request, response);
        } else {
            listTasks(request, response);
        }
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> tasks = taskService.findAll();
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/WEB-INF/views/dashboard/Task/tasks.jsp").forward(request, response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tag> tags = tagService.findAll();
        List<User> users = userService.getRegularUsers();
        request.setAttribute("tags", tags);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/dashboard/Task/createTaskForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTask(request, response);
        }else if ("edit".equals(action)) {
            System.out.println();
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
            response.sendRedirect("tasks?action=list");
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
}
