package com.crud.Learn.service;

import com.crud.Learn.dto.UserDTO;
import com.crud.Learn.model.User;
import com.crud.Learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Save user
    // Convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getMonthly_income());
    }

    // Save or update user
    public UserDTO saveOrUpdateUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setMonthly_income(user.getMonthly_income());
            updatedUser.setUpdated_at(LocalDateTime.now());

            return convertToDTO(userRepository.save(updatedUser));
        } else {
            user.setCreated_at(LocalDateTime.now());
            user.setUpdated_at(LocalDateTime.now());

            return convertToDTO(userRepository.save(user));
        }
    }

    // Get all users as DTOs
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //delete user
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
