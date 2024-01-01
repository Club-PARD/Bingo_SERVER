package com.threefour.bingo.answer.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerGetResponse {

    private Long id;

    private AppUser appUser;

    private String ans;

}
