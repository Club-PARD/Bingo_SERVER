package com.threefour.bingo.worksapce.dto.response;

import com.threefour.bingo.Role;
import com.threefour.bingo.enrollment.entity.Enrollment;
import com.threefour.bingo.worksapce.entity.Workspace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceInfoResponse {

    private Long id;

    private String name;

    private String description;

    private Role role;

    public WorkspaceInfoResponse(Workspace workspace) {
    }
}
