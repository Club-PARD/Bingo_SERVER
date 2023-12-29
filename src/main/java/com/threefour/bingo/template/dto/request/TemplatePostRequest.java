package com.threefour.bingo.template.dto.request;

import com.threefour.bingo.question.dto.request.QuestionRequest;
import com.threefour.bingo.template.domain.TemplateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TemplatePostRequest {

    private String name;

    private Long userId;

    private Long projectId;

    private TemplateType templateType;

    private List<QuestionRequest> questionRequestList;
}
