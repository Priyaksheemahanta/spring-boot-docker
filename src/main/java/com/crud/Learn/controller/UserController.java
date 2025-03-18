package com.crud.Learn.controller;

import com.crud.Learn.dto.LogInDTO;
import com.crud.Learn.dto.UserDTO;
import com.crud.Learn.model.User;
import com.crud.Learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create a user or Update

    @PostMapping
    public UserDTO saveOrUpdateUser(@RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LogInDTO loginDTO) {
        boolean isAuthenticated = userService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login Successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUSer(@RequestBody User user){
        boolean isRegistered = userService.registerUser(user);

        if(isRegistered){
            return ResponseEntity.ok("User registered successfully");
        }else{
            return ResponseEntity.badRequest().body("User with this email already exist");
        }
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.ok("User deleted successfully!");
        }else{
            return ResponseEntity.status(404).body("User not Found!");
        }
    }

}
