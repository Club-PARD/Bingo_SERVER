package com.threefour.bingo.template.dto;

import com.threefour.bingo.question.dto.QuestionDto;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDto {

    private Long id;

    private TemplateType templateType;

    private List<QuestionDto> questionList = new ArrayList<>();

    public TemplateDto(Template template) {
        this.id = template.getId();
        this.templateType = template.getTemplateType();
        this.questionList = template.getQuestionList().stream()
                .map(QuestionDto::new)
                .collect(Collectors.toList());
    }

}
