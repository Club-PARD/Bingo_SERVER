package com.threefour.bingo.answer.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ans;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_question_id")
    private SubQuestion subQuestion;

    //    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "app_user_id")
//    private AppUser appUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;


    @Builder
    public Answer(String ans, SubQuestion subQuestion, AppUser appUser) {
        this.ans = ans;
        this.subQuestion = subQuestion;
        this.appUser = appUser;
    }
}
