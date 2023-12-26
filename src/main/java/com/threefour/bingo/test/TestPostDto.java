package com.threefour.bingo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestPostDto {
    private String title;
    private String content;
    private Integer test;

    public Test toEntity() {
        return Test.builder()
                .test(test)
                .content(content)
                .test(test)
                .build();
    }
}
