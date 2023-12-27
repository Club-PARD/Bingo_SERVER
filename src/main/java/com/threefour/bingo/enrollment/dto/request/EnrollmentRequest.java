package com.threefour.bingo.enrollment.dto.request;

import com.threefour.bingo.Role;
import lombok.Getter;

@Getter
public class EnrollmentRequest {

    private Long userId;

    private Long workspaceId;

    private String code;

    private Role role;
}
