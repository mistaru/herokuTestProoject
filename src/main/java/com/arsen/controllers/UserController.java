package com.arsen.controllers;

import com.arsen.models.User;
import com.arsen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/get/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/get/all")
    public List<User> getAllUser() {
        return service.getAllUser();
    }

    @PostMapping("/save")
    public Long saveUser(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password) {
        return service.newUser(name, email, password);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return service.deleteUserById(id);
    }

    @PatchMapping("/update")
    public String updateUserById(@RequestParam Long id,
                                 @RequestParam String name) {
        return service.updateUserById(id, name);
    }

}
