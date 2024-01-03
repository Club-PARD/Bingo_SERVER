package com.threefour.bingo.enrollment.dto.request;

import com.threefour.bingo.enrollment.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequest {

    private Long userId;

    private Long projectId;

    private String code;

    private Role role;
}
