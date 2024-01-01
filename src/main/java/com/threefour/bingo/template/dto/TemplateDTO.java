package com.threefour.bingo.template.dto;

import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.template.domain.TemplateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDTO {

    private Long id;

    private String name;

//    private TemplateType templateType;

    private List<QuestionDTO> questionList = new ArrayList<>();

}
