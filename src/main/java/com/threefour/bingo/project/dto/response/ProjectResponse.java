package com.threefour.bingo.project.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProjectResponse {

    private Long id;

    private String name;

    private String description;

    private List<AppUser> appUserList;
}
