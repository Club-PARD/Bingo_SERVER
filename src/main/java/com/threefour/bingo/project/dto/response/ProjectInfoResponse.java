package com.threefour.bingo.project.dto.response;

import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.project.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfoResponse {

    private Long id;

    private String name;

    private String description;

    private Role role;

    public ProjectInfoResponse(Project project) {
    }
}
