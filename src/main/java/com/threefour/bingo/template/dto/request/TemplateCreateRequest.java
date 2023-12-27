package com.threefour.bingo.template.dto.request;

import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.TemplateEnum;
import com.threefour.bingo.template.domain.Template;
import lombok.Getter;

import java.util.List;

@Getter
public class TemplateCreateRequest {

    private Long userId;

    private Long projectId;

    private TemplateEnum templateEnum;

    private List<Question> questionList;
}
