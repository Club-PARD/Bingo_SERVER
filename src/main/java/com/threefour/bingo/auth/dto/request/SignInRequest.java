package com.threefour.bingo.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    private String name;

    private String email;

    private String picture;

    private boolean emailVerified;
    
}
