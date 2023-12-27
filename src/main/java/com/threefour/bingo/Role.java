package com.threefour.bingo;

import lombok.Getter;

@Getter
public enum Role {
    TEAM_LEADER(1, "팀장"),
    TEAM_MEMBER(2, "팀원");

    private final int roleId;
    private final String roleName;

    Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}


