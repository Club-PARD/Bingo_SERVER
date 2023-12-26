package com.threefour.bingo.worksapce.dto.response;

import com.threefour.bingo.enrollment.entity.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceResponse {
    private Long id;
    private String name;
    private String description;
    private String code;
}
