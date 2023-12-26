package com.threefour.bingo.appUser.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserInfoResponse {
    private String name;
    private String email;
}
