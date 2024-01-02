package com.threefour.bingo.template.dto.response;

import com.threefour.bingo.template.domain.TemplateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateInfoResponse {
    private String name;
    private TemplateType templateType;

}
