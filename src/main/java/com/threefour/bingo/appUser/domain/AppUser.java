package com.threefour.bingo.appUser.domain;

import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String token;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Enrollment> enrollmentList = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<SubQuestion> subQuestionList = new ArrayList<>();

    @Builder
    public AppUser(String name, String email, String token) {

        this.name = name;

        this.email = email;

        this.token = token;
    }

    public AppUser(Long id, String name, String email, String token) {

        this.id = id;

        this.name = name;

        this.email = email;

        this.token = token;
    }

    @Builder
    public AppUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void update(String token) {
        this.token = token;
    }
}
