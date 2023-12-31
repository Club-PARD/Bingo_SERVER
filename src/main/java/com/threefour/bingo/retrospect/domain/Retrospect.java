package com.threefour.bingo.retrospect.domain;

import com.threefour.bingo.appUser.domain.AppUser;

import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.template.domain.Template;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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

    @OneToOne(mappedBy = "retrospect", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Template template;

    @Enumerated(EnumType.STRING)
    private TeamEvaluation teamEvaluation;

}
