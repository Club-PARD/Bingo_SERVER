package com.threefour.bingo.question.domain;

import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.template.domain.Template;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mainQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retrospect_id")
    private Retrospect retrospect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;

    @OneToMany(mappedBy = "subQuestion")
    private List<SubQuestion> subQuestionList = new ArrayList<>();
}

