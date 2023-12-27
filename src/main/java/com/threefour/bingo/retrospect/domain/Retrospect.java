package com.threefour.bingo.retrospect.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.TemplateEnum;
import com.threefour.bingo.project.domain.Project;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Retrospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private TemplateEnum template;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    private Enrollment enrollment;

    @OneToMany(mappedBy = "retrospect")
    private List<Question> questionList = new ArrayList<>();
}
