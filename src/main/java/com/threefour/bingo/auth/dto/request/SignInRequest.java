package com.threefour.bingo.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    private String name;

    private String email;

    private boolean emailVerified;
}
