package com.threefour.bingo.worksapce.domain;

import com.threefour.bingo.enrollment.domain.Enrollment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String code;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private List<Enrollment> enrollmentList = new ArrayList<>();

    @Builder
    public Workspace(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }
}
