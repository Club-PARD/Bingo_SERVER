package com.threefour.bingo.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TagDTO {

    private Long id;

    private String name;

    private Integer count;

//    private boolean isSelected;
}
