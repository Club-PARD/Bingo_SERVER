package com.threefour.bingo.auth.controller;

import com.threefour.bingo.test.TestGetDto;
import com.threefour.bingo.test.TestPostDto;
import com.threefour.bingo.test.TestUpdateDto;
import com.threefour.bingo.auth.service.AuthService;
import com.threefour.bingo.auth.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j

public class LoginController {
    private final AuthService loginService;
    private final TokenProvider tokenProvider;

    //    @GetMapping("/code/{registrationId}")
//    public ResponseEntity<SignInResponse> googleLogin(@RequestParam String code, @PathVariable String registrationId) {
//        log.info("하이루");
//        log.info("code" + code);
//        SignInResponse response = loginService.signIn(code, registrationId);
//        log.info(response.getJwtToken());
//        log.info(String.valueOf(response.getExprTime()));
//        log.info(response.getAppUser().getName());
//
//        return ResponseEntity.ok()
//                .body(response);
//    }
}
