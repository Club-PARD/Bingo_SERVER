package com.threefour.bingo.retrospect.dto.response;

import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RetrospectPostResponse {

    private AppUserInfoResponse appUser;

    private List<AnswerDTO> answerList;

}
