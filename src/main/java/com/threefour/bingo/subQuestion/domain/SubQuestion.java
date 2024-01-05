package com.threefour.bingo.subQuestion.domain;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @OneToMany(mappedBy = "subQuestion", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @Builder
    public SubQuestion(String subQuestion, Question question) {
        this.subQuestion = subQuestion;
        this.question = question;
    }

}
