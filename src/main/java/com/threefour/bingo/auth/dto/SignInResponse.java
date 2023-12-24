package com.threefour.bingo.auth.dto;

import com.threefour.bingo.appUser.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private String jwtToken;
    private Integer exprTime;
    private AppUser appUser;
}
