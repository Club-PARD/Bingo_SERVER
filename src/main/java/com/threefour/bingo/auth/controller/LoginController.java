package com.threefour.bingo.auth.controller;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.auth.dto.request.SignInRequest;
import com.threefour.bingo.auth.dto.response.SignInResponse;
import com.threefour.bingo.auth.dto.response.SignOutResponse;
import com.threefour.bingo.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class LoginController {

    private final AuthService authService;

    @PostMapping("/signIn")
    public ResponseDto<SignInResponse> signIn(@RequestBody final SignInRequest request) {

        ResponseDto<SignInResponse> response = authService.signIn(request);

        return response;
    }

    @PostMapping("/signOut/{id}")
    public ResponseDto<SignOutResponse> signOut(@PathVariable final Long id) {

        ResponseDto<SignOutResponse> response = authService.signOut(id);

        return response;
    }
}
