package com.threefour.bingo.answer.dto.response;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.appUser.dto.response.AppUserAnsResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {

    private Long id;

    private String ams;

//    private String writer;

    private AppUserAnsResponse appUserAnsResponse;

    public AnswerResponse(Answer answer) {
        this.id = answer.getId();
        this.ams = answer.getAns();
        this.appUserAnsResponse = new AppUserAnsResponse(answer.getAppUser().getName(), answer.getAppUser().getPicture());
    }

}