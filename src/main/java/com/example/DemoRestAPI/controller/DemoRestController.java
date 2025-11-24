package com.example.DemoRestAPI.controller;


import com.example.DemoRestAPI.entity.User;
import com.example.DemoRestAPI.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/users")
@RestController
public class DemoRestController {

    private final UserRepository userRepository;

    // Внедряем зависимость через конструктор
    public DemoRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Получение всех пользователей (GET /api/users)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Получение одного пользователя по ID (GET /api/users/{id})
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok) // Если пользователь найден
                .orElse(ResponseEntity.notFound().build()); // Если не найден
    }

    // Создание нового пользователя (POST /api/users)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Обновление существующего пользователя (PUT /api/users/{id})
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setAge(updatedUser.getAge());
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление пользователя (DELETE /api/users/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
