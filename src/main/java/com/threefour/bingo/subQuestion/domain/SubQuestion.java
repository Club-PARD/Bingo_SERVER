package com.threefour.bingo.subQuestion.domain;

import com.threefour.bingo.question.domain.Question;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class SubQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_question_id")
    private Question question;

    private String answer;
}
