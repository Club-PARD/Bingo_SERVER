package com.threefour.bingo.enrollment.dto.response;

import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.project.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {

    private AppUser appUser;

    private Project project;

    private Role role;

}
