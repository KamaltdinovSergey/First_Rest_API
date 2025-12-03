package com.example.DemoRestAPI.controller;

import com.example.DemoRestAPI.entity.User;
import com.example.DemoRestAPI.repository.UserRepository;
import com.example.DemoRestAPI.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("api/users")
public class DemoRestController {

    private final UserService userService;

    public DemoRestController (UserService userService){
        this.userService = userService;
    }

    // Получение всех пользователей (GET /api/users)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // Получение одного пользователя по ID (GET /api/users/{id})
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    // Создание нового пользователя (POST /api/users)
    @PostMapping()
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }

    // Обновление существующего пользователя (PUT /api/users/{id})
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.findById(id);
    }
}
