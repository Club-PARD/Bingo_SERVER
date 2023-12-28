package com.threefour.bingo.template.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
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

    private AppUser appUser;

    private Project project;

    private TemplateType templateType;

    private List<Question> questionList;

    public TemplateResponse(AppUser appUser, Project project, TemplateType templateType,
                            List<Question> questionList) {

        this.appUser = this.getAppUser();
        this.project = this.getProject();
        this.templateType = this.getTemplateType();
        this.questionList = this.getQuestionList();
    }
}
