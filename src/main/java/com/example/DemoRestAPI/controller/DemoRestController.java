package com.example.DemoRestAPI.controller;

import com.example.DemoRestAPI.entity.User;
import com.example.DemoRestAPI.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
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
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok) // Если пользователь найден
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание нового пользователя (POST /api/users)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    // Обновление существующего пользователя (PUT /api/users/{id})
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setAge(updatedUser.getAge());
                    return ResponseEntity.ok(userService.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление пользователя (DELETE /api/users/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    userService.delete(user);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
