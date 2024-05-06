package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(Long userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setUnique_id(updatedUser.getUnique_id());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + userId);
        }
    }

    public Optional<User> getUserById(Long userId) {
        return Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user found with id " + userId)));
    }

}
