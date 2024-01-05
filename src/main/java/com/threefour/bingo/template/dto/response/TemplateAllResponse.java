package com.threefour.bingo.template.dto.response;

import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.template.domain.TemplateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateAllResponse {

    private Long id;

    private String name;

    private List<Integer> isWritedList;

    private TemplateType templateType;

    private List<QuestionDTO> questionList;


}
