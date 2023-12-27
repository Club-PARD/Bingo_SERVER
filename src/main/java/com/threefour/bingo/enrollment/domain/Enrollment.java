package com.threefour.bingo.enrollment.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.project.domain.Project;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private Role role;

    @Builder
    public Enrollment(AppUser appUser, Project project, Role role) {
        this.appUser = appUser;
        this.project = project;
        this.role = role;
    }
}
