package com.threefour.bingo.auth.controller;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.auth.dto.SignInResponse;
import com.threefour.bingo.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login/oauth2", produces = "application/json")
@Slf4j

public class LoginController {
    private final AuthService loginService;

    @GetMapping("/code/{registrationId}")
    public ResponseEntity<SignInResponse> googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        log.info("code" + code);
        SignInResponse response = loginService.signIn(code, registrationId);

        log.info(response.getJwtToken());
        log.info(String.valueOf(response.getExprTime()));
        log.info(response.getAppUser().getName());

        return ResponseEntity.ok()
                .body(response);
    }
}
