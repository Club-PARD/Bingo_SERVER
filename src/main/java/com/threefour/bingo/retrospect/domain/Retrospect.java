package com.threefour.bingo.retrospect.domain;

import com.threefour.bingo.appUser.domain.AppUser;

import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.tag.TeamEvaluation;
import com.threefour.bingo.template.domain.Template;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Retrospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;

    @Enumerated(EnumType.STRING)
    private TeamEvaluation teamEvaluation;

    @Builder
    public Retrospect(AppUser appUser, Project project, Template template) {
        this.appUser = appUser;
        this.project = project;
        this.template = template;
    }

}
