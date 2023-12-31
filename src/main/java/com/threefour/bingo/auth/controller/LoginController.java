package com.threefour.bingo.auth.controller;

import com.threefour.bingo.ResponseDto;
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
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody final SignInRequest request) {

        final SignInResponse response = authService.signIn(request);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/signOut/{id}")
    public ResponseEntity<SignOutResponse> signOut(@PathVariable final Long id) {

        final SignOutResponse response = authService.signOut(id);

        return ResponseEntity.ok()
                .body(response);
    }
}
