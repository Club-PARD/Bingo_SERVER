package com.threefour.bingo.auth.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

    private Integer exprTime;

    private AppUser appUser;
}
