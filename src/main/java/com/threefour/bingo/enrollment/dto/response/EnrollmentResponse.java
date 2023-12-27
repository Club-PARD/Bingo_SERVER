package com.threefour.bingo.enrollment.dto.response;

import com.threefour.bingo.Role;
import com.threefour.bingo.appUser.entity.AppUser;
import com.threefour.bingo.worksapce.entity.Workspace;
import lombok.Getter;

@Getter
public class EnrollmentResponse {

    private AppUser appUser;

    private Workspace workspace;

    private Role role;

}
