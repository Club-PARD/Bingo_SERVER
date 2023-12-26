package com.threefour.bingo.worksapce.entity;

import com.threefour.bingo.enrollment.entity.Enrollment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Builder
    public Workspace(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
