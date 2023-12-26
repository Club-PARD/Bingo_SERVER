package com.threefour.bingo.auth.controller;

import com.threefour.bingo.auth.dto.request.SignInRequest;
import com.threefour.bingo.auth.dto.request.SignOutRequest;
import com.threefour.bingo.auth.dto.response.SignInResponse;
import com.threefour.bingo.auth.dto.response.SignOutResponse;
import com.threefour.bingo.test.TestGetDto;
import com.threefour.bingo.test.TestPostDto;
import com.threefour.bingo.test.TestUpdateDto;
import com.threefour.bingo.auth.service.AuthService;
import com.threefour.bingo.auth.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j

public class LoginController {
    private final AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) {
        SignInResponse response = authService.signIn(request);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/signOut")
    public ResponseEntity<SignOutResponse> signOut(@RequestBody SignOutRequest request) {
        SignOutResponse response = authService.signOut(request);

        return ResponseEntity.ok()
                .body(response);
    }
}
