package org.sicario.repository.interfaces;

import org.sicario.model.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);
    User findById(Long id);
    List<User> findAll();
    void update(User user);
    void delete(User user);
    Optional<User> findByEmail(String email);
}