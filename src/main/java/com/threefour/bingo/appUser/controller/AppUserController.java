package com.threefour.bingo.appUser.controller;

import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import com.threefour.bingo.appUser.service.AppUserService;
import com.threefour.bingo.auth.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appUser")
//@CrossOrigin(origins = "http://localhost:3000")
public class AppUserController {

    private final AppUserService appUserService;
    private final TokenProvider tokenProvider;

    @GetMapping("/{id}")
    public ResponseEntity<AppUserInfoResponse> getUserInfo(@RequestHeader(value = "Authorization") final String token, @PathVariable final Long id) {
//        final String email = tokenProvider.validate(token.substring(7));
        log.info("토큰: {}", token);
        final AppUserInfoResponse response = appUserService.getUserInfo(id);

        return ResponseEntity.ok()
                .body(response);

    }
}
