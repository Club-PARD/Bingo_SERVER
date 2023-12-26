package com.threefour.bingo.appUser.entity;

import com.threefour.bingo.enrollment.entity.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String token;

    @Builder
    public AppUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
