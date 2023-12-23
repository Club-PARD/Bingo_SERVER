package com.threefour.bingo.auth.controller;

import com.threefour.bingo.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login/oauth2", produces = "application/json")
@Slf4j

public class LoginController {
    private final LoginService loginService;

    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        log.info("구글 로그인 컨트롤러 작동");
        log.info("code" + code);
        loginService.socialLogin(code, registrationId);
    }
}
