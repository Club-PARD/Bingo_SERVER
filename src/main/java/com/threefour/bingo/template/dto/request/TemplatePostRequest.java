package com.threefour.bingo.template.dto.request;

import com.threefour.bingo.question.dto.request.QuestionRequest;
import com.threefour.bingo.template.domain.TemplateType;
import lombok.Getter;

import java.util.List;

@Getter
public class TemplatePostRequest {

//    private Long templateId;

    private Long userId;

    private Long projectId;

    private TemplateType templateType;

    private List<QuestionRequest> questionRequestList;
}
