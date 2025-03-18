package com.crud.Learn.service;

import com.crud.Learn.dto.UserDTO;
import com.crud.Learn.model.User;
import com.crud.Learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Inject PasswordEncoder
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        int cibilScore = (user.getCibil_score() != null) ? user.getCibil_score() : 0;  // Set 0 or handle it as per your business requirement.
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), cibilScore, user.getMonthly_income());
    }

    public boolean registerUser(User user){
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            return false;
        }
        if (user.getCibil_score() != null && (user.getCibil_score() < 300 || user.getCibil_score() > 900)) {
            throw new IllegalArgumentException("Cibil score must be between 300 and 900, or null.");
        }
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());

        userRepository.save(user);
        return true;
    }

    // Save or update user
    public UserDTO saveOrUpdateUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());

            // Encrypt new password only if provided
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            updatedUser.setMonthly_income(user.getMonthly_income());
            updatedUser.setUpdated_at(LocalDateTime.now());

            return convertToDTO(userRepository.save(updatedUser));
        } else {
            // Encrypt the new password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreated_at(LocalDateTime.now());
            user.setUpdated_at(LocalDateTime.now());

            return convertToDTO(userRepository.save(user));
        }
    }

    // Authentication (Login)
    public boolean authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword()); // Compare hashed passwords
        }
        return false;
    }

    // Get all users as DTOs
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Delete user
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
