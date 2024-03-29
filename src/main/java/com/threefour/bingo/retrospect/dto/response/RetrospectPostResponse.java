package com.threefour.bingo.retrospect.dto.response;

import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.appUser.dto.response.AppUserResponse;
import com.threefour.bingo.tag.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RetrospectPostResponse {

    private AppUserResponse appUser;

    private List<AnswerDTO> answerList;

    private List<TagDTO> tagList;

}
