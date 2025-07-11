package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;

// Marks this class as a REST controller (handles HTTP requests and returns JSON responses)
@RestController

// All routes in this class will start with /api/users
@RequestMapping("/api/users")

// Allows requests from React frontend (running on port 3000) to access this backend
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    // Injects the UserRepository so we can interact with the database
    @Autowired
    private UserRepository userRepo;

    // =============================
    // GET /api/users
    // Fetches and returns all users from the database
    // =============================
    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll(); // Calls JPA to return all user records
    }

    // =============================
    // POST /api/users
    // Creates a new user with data sent in the request body
    // =============================
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepo.save(user); // Saves the new user to the database
    }

    // =============================
    // PUT /api/users/{id}
    // Updates the name of an existing user by ID
    // =============================
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserName(@PathVariable Long id, @RequestBody User updatedUser) {
        // First, check if user exists with the given ID
        return userRepo.findById(id)
                .map(user -> {
                    // Only update the name field
                    user.setName(updatedUser.getName());
                    // Save the updated user back to DB
                    User saved = userRepo.save(user);
                    return ResponseEntity.ok(saved); // Return 200 OK with updated user
                })
                // If user not found, return 404 Not Found
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =============================
    // DELETE /api/users/{id}
    // Deletes a user by their ID
    // =============================
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id); // Delete the user with the given ID from the database
    }
}
