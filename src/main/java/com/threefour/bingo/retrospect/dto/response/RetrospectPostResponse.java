package com.threefour.bingo.retrospect.dto.response;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RetrospectPostResponse {

    private AppUser appUser;

    private List<AnswerDTO> answerList;

}
