package com.threefour.bingo.worksapce.dto.request;

import com.threefour.bingo.appUser.entity.AppUser;
import com.threefour.bingo.worksapce.entity.Workspace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceRequest {
    private String name;
    private String description;

    public Workspace toEntity() {
        return Workspace.builder()
                .name(name)
                .description(description)
                .build();
    }
}
