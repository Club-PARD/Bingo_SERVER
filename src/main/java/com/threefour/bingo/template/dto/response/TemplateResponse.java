package com.threefour.bingo.template.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.TemplateEnum;
import com.threefour.bingo.worksapce.domain.Workspace;
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

    private Workspace workspace;

    private TemplateEnum templateEnum;

    private List<Question> questionList;

    public TemplateResponse(AppUser appUser, Workspace workspace, TemplateEnum templateEnum,
                            List<Question> questionList) {

        this.appUser = this.getAppUser();
        this.workspace = this.getWorkspace();
        this.templateEnum = this.getTemplateEnum();
        this.questionList = this.getQuestionList();
    }
}
