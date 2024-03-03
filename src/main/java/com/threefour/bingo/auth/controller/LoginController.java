package com.threefour.bingo.auth.controller;

import com.threefour.bingo.auth.dto.request.SignInRequest;
import com.threefour.bingo.auth.dto.response.SignInResponse;
import com.threefour.bingo.auth.dto.response.SignOutResponse;
import com.threefour.bingo.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class LoginController {

    private final AuthService authService;

    // 로그인 컨트롤러
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody final SignInRequest request) {

        final SignInResponse response = authService.signIn(request);

        return ResponseEntity.ok()
                .body(response);
    }

    // 로그아웃 컨트롤러
    @PostMapping("/signOut/{appUserId}")
    public ResponseEntity<SignOutResponse> signOut(@PathVariable final Long appUserId) {

        final SignOutResponse response = authService.signOut(appUserId);

        return ResponseEntity.ok().body(response);
    }
}
