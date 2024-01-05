package com.threefour.bingo.project.dto.response;

import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectOneResponse {

    private Long id;

    private String name;

    private String description;

    private Role role;

    private List<TagDTO> tagList;

    private String code;


    public ProjectOneResponse(Long id, String name, String description, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

}
