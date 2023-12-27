package com.threefour.bingo.appUser.domain;

import com.threefour.bingo.enrollment.domain.Enrollment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String token;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Enrollment> enrollmentList = new ArrayList<>();

    @Builder
    public AppUser(String name, String email, String token) {

        this.name = name;

        this.email = email;

        this.token = token;
    }
}
