package com.threefour.bingo.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Integer test;

    @Builder
    public Test(String title, String content, Integer test) {
        this.title = title;
        this.content = content;
        this.test = test;
    }

    public Test(TestPostDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.test = dto.getTest();
    }
}
