package com.threefour.bingo.project.dto.response;

import com.threefour.bingo.enrollment.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAllResponse {
    private Long id;

    private String name;

    private String description;

    private String picture;

    private Role role;

    private String code;


    public ProjectAllResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
