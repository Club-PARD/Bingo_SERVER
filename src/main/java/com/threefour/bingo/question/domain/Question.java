package com.threefour.bingo.question.domain;

import com.threefour.bingo.subQuestion.domain.SubQuestion;
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

    @OneToMany(mappedBy = "subQuestion")
    private List<SubQuestion> subQuestionList = new ArrayList<>();
}
