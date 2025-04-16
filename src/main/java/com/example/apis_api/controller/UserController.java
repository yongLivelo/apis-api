package com.example.apis_api.controller;

import com.example.apis_api.model.User;
import com.example.apis_api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepo repo;


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User request) {
        Optional<User> user = repo.findByUsername(request.getUsername());
        if (user.isEmpty() || !((user.get().getPassword()).equals(request.getPassword()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User request) {
        Optional<User> existing = repo.findByUsername(request.getUsername());
        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (request.getUsername() == null || request.getPassword() == null || request.getRoles() == null || request.getId() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRoles(request.getRoles());
        repo.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = repo.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User deleted = optionalUser.get();
        repo.deleteById(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = repo.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(users);
    }

}

