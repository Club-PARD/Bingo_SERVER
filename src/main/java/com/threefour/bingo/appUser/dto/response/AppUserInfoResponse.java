package com.threefour.bingo.appUser.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserInfoResponse {

    private Long id;

    private String name;

    private String email;
}
