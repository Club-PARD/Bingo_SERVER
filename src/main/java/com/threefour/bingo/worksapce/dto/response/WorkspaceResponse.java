package com.threefour.bingo.worksapce.dto.response;

import com.threefour.bingo.appUser.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WorkspaceResponse {

    private Long id;

    private String name;

    private String description;

    private List<AppUser> appUserList;
}
