package com.threefour.bingo.template.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.TemplateEnum;
import com.threefour.bingo.worksapce.domain.Workspace;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    private TemplateEnum templateEnum;

    @OneToMany(mappedBy = "questionAnswer", cascade = CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();

    @Builder
    public Template(AppUser appUser, Workspace workspace,
                    TemplateEnum templateEnum, List<Question> questionList) {

        this.appUser = appUser;
        this.workspace = workspace;
        this.templateEnum = templateEnum;
        this.questionList = questionList;
    }
}
