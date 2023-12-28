package com.threefour.bingo.question.domain;

import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.template.domain.Template;
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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mainQuestion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;

    @OneToMany(mappedBy = "subQuestion")
    private List<SubQuestion> subQuestionList = new ArrayList<>();

    @Builder
    public Question(String mainQuestion, Template template, List<SubQuestion> subQuestionList) {

        this.mainQuestion = mainQuestion;
        this.template = template;
        this.subQuestionList = subQuestionList;
    }

    public void updateSubQuestionList(List<SubQuestion> subQuestionList) {

        this.subQuestionList = subQuestionList;
    }
}

