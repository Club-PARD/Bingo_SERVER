package com.threefour.bingo.retrospect.dto.response;

import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.answer.dto.response.AnswerGetResponse;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.question.dto.response.QuestionResponse;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.dto.TemplateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RetrospectGetResponse {
    private Long id;

    private TemplateDTO template;

}
