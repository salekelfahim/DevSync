package org.sicario.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sicario.exceptions.UserNullException;
import org.sicario.exceptions.UserAlreadyExistsException;
import org.sicario.exceptions.InvalidUserDataException;
import org.sicario.model.entities.User;
import org.sicario.model.enums.UserRole;
import org.sicario.repository.interfaces.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_whenUserIsNull_shouldThrowUserNullException() {
        User nullUser = null;

        UserNullException exception = assertThrows(UserNullException.class,
                () -> userService.createUser(nullUser));
        assertEquals("User cannot be null", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void createUser_whenUserHasId_shouldThrowInvalidUserDataException() {

        User userWithId = new User("test", "test", "test@test.com", "qwerty123", UserRole.USER);
        userWithId.setId(1L);

        InvalidUserDataException exception = assertThrows(InvalidUserDataException.class,
                () -> userService.createUser(userWithId));
        assertEquals("New user should not have an ID", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void createUser_whenUserAlreadyExists_shouldThrowUserAlreadyExistsException() {

        User existingUser = new User("test", "test", "test@test.com", "qwerty123", UserRole.USER);
        when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(Optional.of(existingUser));

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> userService.createUser(existingUser));
        assertEquals("User with email test@test.com already exists", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test 
    void createUser_whenUserIsValid_shouldSaveSuccessfully() {

        User validUser = new User("test", "test", "test@test.com", "qwerty123", UserRole.USER);
        when(userRepository.findByEmail(validUser.getEmail())).thenReturn(Optional.empty());

        userService.createUser(validUser);

        verify(userRepository, times(1)).save(validUser);
    }
}