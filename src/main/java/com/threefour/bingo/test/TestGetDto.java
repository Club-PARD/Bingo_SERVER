package com.threefour.bingo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGetDto {
    private String title;
    private String content;
    private Integer test;
}
