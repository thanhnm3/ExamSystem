package com.example.exam.controller;

import com.example.exam.dto.UserDto;
import com.example.exam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for user management")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    @Operation(summary = "Register new user", description = "Create a new user account")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.registerUser(userDto);
        return ResponseEntity.ok(createdUser);
    }
    
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve all users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by ID")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/sorted-by-score")
    @Operation(summary = "Get users sorted by score", description = "Retrieve users sorted by average score in descending order")
    public ResponseEntity<List<UserDto>> getUsersSortedByScore() {
        List<UserDto> users = userService.getUsersSortedByScore();
        return ResponseEntity.ok(users);
    }
} 