package com.threefour.bingo.template.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.template.domain.TemplateType;
import com.threefour.bingo.project.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateResponse {

    private Long id;

    private String name;

    private TemplateType templateType;

    private List<QuestionDTO> questionList;

}
