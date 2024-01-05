package com.threefour.bingo.project.dto.request;

import com.threefour.bingo.project.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateRequest {

    private Long userId;

    private String name;

    private String description;

    private String picture;

    private String code;

//    private Long projectId;

    private List<String> tagList;

    public Project toEntity() {
        return Project.builder()
                .name(name)
                .description(description)
                .picture(picture)
                .code(code)
                .build();
    }
}
