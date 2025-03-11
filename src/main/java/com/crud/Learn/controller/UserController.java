package com.crud.Learn.controller;

import com.crud.Learn.dto.UserDTO;
import com.crud.Learn.model.User;
import com.crud.Learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
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

    // Delete user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
