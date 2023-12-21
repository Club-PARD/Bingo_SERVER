package com.threefour.bingo.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int auth; //1: 팀장, 2: 팀원
    private String name;
    private String email;
    private String key;

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User update(String name) {
        this.name = name;

        return this;
    }
}
