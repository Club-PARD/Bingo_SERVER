package com.threefour.bingo.template.dto.request;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.TemplateEnum;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.project.domain.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TemplateRequest {

    private AppUser appUser;

    private Project project;

    private TemplateEnum templateEnum;

    private List<Question> questionList;

    public Template toEntity() {
        return Template.builder()
                .appUser(appUser)
                .project(project)
                .templateEnum(templateEnum)
                .questionList(questionList)
                .build();
    }
}
