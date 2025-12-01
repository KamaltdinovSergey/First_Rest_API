package com.example.DemoRestAPI.entity;

import jakarta.persistence.*;
import lombok.*;

@Data

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //автоматическая генерация id
    private Long id;

    private String name;

    private String email;

    private Integer age;
}
