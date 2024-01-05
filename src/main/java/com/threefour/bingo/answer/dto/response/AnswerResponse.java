package com.threefour.bingo.answer.dto.response;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {

    private Long id;

    private String ams;

    private String writer;

//    private AppUserInfoResponse appUserInfoResponse;

    public AnswerResponse(Answer answer) {
        this.id = answer.getId();
        this.ams = answer.getAns();
        this.writer = answer.getAppUser().getName();
    }

}