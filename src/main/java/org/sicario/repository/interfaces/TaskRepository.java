package org.sicario.repository.interfaces;

import org.sicario.model.entities.Task;
import org.sicario.model.entities.User;
import org.sicario.model.enums.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void save(Task task);
    Optional<Task> findById(long id);
    List<Task> findAll();
    void delete(Task task);
    void update(Task task);
    List<Task> findByStatusAndUser(TaskStatus status, User user);
}
