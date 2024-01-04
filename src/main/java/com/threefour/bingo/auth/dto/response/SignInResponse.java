package com.threefour.bingo.auth.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

    private Integer exprTime;

    private AppUserInfoResponse appUser;

    private String token;

    private Integer isSigned;
}
