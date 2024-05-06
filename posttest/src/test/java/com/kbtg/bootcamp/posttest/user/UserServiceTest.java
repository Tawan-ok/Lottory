package com.kbtg.bootcamp.posttest.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUser() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUser();

        assertEquals(2, users.size());
    }

    @Test
    void registerUser() {
        User newUser = new User();
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User savedUser = userService.registerUser(newUser);

        assertEquals(newUser, savedUser);
    }

    @Test
    void editUserFound() {
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setUsername("existing");
        User updatedUser = new User();
        updatedUser.setUsername("updated");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.editUser(userId, updatedUser);

        assertEquals("updated", result.getUsername());
    }

    @Test
    void editUserNotFound() {
        Long userId = 1L;
        User updatedUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.editUser(userId, updatedUser);
        });

        String expectedMessage = "User not found with id " + userId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getUserByIdFound() {
        Long userId = 1L;
        Optional<User> user = Optional.of(new User());
        when(userRepository.findById(userId)).thenReturn(user);

        Optional<User> foundUser = userService.getUserById(userId);

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser);
    }

    @Test
    void getUserByIdNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getUserById(userId).orElseThrow(() -> new NoSuchElementException("No user found with id " + userId));
        });

        String expectedMessage = "No user found with id " + userId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}