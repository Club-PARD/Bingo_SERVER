package com.threefour.bingo.auth.controller;

import com.threefour.bingo.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login/oauth2", produces = "application/json")

public class LoginController {
    private final LoginService loginService;

    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        System.out.println(code);
        loginService.socialLogin(code, registrationId);
    }
}
