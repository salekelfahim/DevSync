package org.sicario.service;

import org.sicario.model.entities.User;
import org.sicario.repository.interfaces.UserRepository;
import java.util.List;
import java.util.Optional;

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.delete(user);
        }else {
            System.out.println("User not found");
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
