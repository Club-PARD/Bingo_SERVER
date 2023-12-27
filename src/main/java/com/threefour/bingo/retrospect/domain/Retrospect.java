package com.threefour.bingo.retrospect.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.TemplateEnum;
import com.threefour.bingo.worksapce.domain.Workspace;
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
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    private Enrollment enrollment;

    @OneToMany(mappedBy = "questionAnswer", cascade = CascadeType.ALL)
    private List<Question> questionAnswerList = new ArrayList<>();
}
