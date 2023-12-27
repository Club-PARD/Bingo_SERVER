package com.threefour.bingo.enrollment.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.worksapce.domain.Workspace;
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
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    private Role role;

    @Builder
    public Enrollment(AppUser appUser, Workspace workspace, Role role) {
        this.appUser = appUser;
        this.workspace = workspace;
        this.role = role;
    }
}
