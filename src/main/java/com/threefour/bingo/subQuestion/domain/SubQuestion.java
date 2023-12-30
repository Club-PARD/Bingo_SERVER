package com.threefour.bingo.subQuestion.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
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
    @JoinColumn(name = "main_question_id")
    private Question question;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @Builder
    public SubQuestion(String subQuestion, String answer, Question question) {
        this.subQuestion = subQuestion;
        this.answer = answer;
        this.question = question;
    }

    public void update(String answer) {
        this.answer = answer;
    }
}
