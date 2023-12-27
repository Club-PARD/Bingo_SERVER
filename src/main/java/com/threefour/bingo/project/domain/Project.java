package com.threefour.bingo.project.domain;

import com.threefour.bingo.enrollment.domain.Enrollment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String code;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Enrollment> enrollmentList = new ArrayList<>();

    @Builder
    public Project(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }
}
