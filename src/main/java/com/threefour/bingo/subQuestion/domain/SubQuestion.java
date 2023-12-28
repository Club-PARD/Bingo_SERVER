package com.threefour.bingo.subQuestion.domain;

import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.domain.Template;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_xquestion_id")
    private Question question;

    private String answer;


    @Builder
    public SubQuestion(String subQuestion, String answer, Question question) {
        this.subQuestion = subQuestion;
        this.answer = answer;
        this.question = question;
    }
}
