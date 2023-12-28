package com.threefour.bingo.template.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.project.domain.Project;
import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private TemplateType templateType;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();

    @Builder
    public Template(AppUser appUser, Project project,
                    TemplateType templateType, List<Question> questionList) {

        this.appUser = appUser;
        this.project = project;
        this.templateType = templateType;
        this.questionList = questionList;
    }

    public void updateQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
