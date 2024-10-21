package org.sicario.service;

import org.sicario.exceptions.InvalidUserDataException;
import org.sicario.exceptions.UserAlreadyExistsException;
import org.sicario.exceptions.UserNullException;
import org.sicario.model.entities.User;
import org.sicario.model.enums.UserRole;
import org.sicario.repository.interfaces.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
    }

    public void createUser(User user) {
        if (user == null) {
            throw new UserNullException("User cannot be null");
        }

        if (user.getId() != null) {
            throw new InvalidUserDataException("New user should not have an ID");
        }

        Optional<User> existingUser = getUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }

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

    public List<User> getRegularUsers() {
        List<User> allUsers = getAllUsers();
        return allUsers.stream()
                .filter(user -> user.getRole().equals(UserRole.USER))
                .collect(Collectors.toList());
    }
}
