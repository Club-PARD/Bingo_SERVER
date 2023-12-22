package com.threefour.bingo.appUser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer auth; //1: 팀장, 2: 팀원
    private String name;
    private String email;
    private String key;

    @Builder
    public AppUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public AppUser update(String name) {
        this.name = name;

        return this;
    }
}
