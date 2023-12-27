package com.threefour.bingo.template.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.TemplateEnum;
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

    private TemplateEnum templateEnum;

    private List<Question> questionList;

    public TemplateResponse(AppUser appUser, Project project, TemplateEnum templateEnum,
                            List<Question> questionList) {

        this.appUser = this.getAppUser();
        this.project = this.getProject();
        this.templateEnum = this.getTemplateEnum();
        this.questionList = this.getQuestionList();
    }
}
