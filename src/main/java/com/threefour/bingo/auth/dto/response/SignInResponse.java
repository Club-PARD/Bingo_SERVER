package com.threefour.bingo.auth.dto.response;

import com.threefour.bingo.appUser.dto.response.AppUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

    private AppUserResponse appUser;

    private Integer isSigned;

    private String token;

    private Integer exprTime;
}
