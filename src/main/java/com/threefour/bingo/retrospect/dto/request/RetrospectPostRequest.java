package com.threefour.bingo.retrospect.dto.request;

import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.tag.dto.request.TagPostRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RetrospectPostRequest {

    private Long appUserId;

    private Long projectId;

    private Long templateId;

    private List<AnswerDTO> answerList;

    private List<TagPostRequest> tagList;

}
