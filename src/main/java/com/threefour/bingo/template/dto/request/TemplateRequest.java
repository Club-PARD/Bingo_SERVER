package com.threefour.bingo.template.dto.request;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.dto.QuestionDto;
import com.threefour.bingo.template.domain.TemplateType;
import com.threefour.bingo.project.domain.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TemplateRequest {

    private AppUser appUser;

    private Project project;

    private TemplateType templateType;

    private List<QuestionDto> questionList;

}
