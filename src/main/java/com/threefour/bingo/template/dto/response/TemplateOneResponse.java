package com.threefour.bingo.template.dto.response;

import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.template.domain.TemplateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateOneResponse {

    private Long id;

    private String name;

    private TemplateType templateType;

    private List<QuestionDTO> questionList;

    private List<TagDTO> tagList;

}
