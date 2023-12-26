package com.threefour.bingo.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
"email" : string,
"email-verified" : boolean,
"name" : string,
"picture" : string,
"jti" : string
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    private String name;
    private String email;
    private boolean emailVerified;
}
