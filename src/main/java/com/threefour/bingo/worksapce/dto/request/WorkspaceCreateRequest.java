package com.threefour.bingo.worksapce.dto.request;

import com.threefour.bingo.worksapce.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceCreateRequest {

    private String name;

    private String description;

    private String code;

    public Workspace toEntity() {
        return Workspace.builder()
                .name(name)
                .description(description)
                .code(code)
                .build();
    }
}
