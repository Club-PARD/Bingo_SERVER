package com.threefour.bingo.retrospect.dto.response;

import com.threefour.bingo.answer.dto.response.AnswerResponse;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.template.dto.TemplateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RetrospectGetResponse {

//    private Long id;

    private List<TagDTO> tagDTOList;

    private TemplateDTO template;

}
