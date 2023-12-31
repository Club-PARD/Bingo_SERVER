package com.threefour.bingo.tag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TagDTO {

    private Long id;

    private String name;

    private Integer count;

    private boolean isSelected;
}
