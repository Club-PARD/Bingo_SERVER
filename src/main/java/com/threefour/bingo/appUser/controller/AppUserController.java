package com.threefour.bingo.appUser.controller;

import com.threefour.bingo.appUser.dto.response.AppUserResponse;
import com.threefour.bingo.appUser.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appUser")
@Slf4j
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/{appUserId}")
    public ResponseEntity<AppUserResponse> getUserInfo(@RequestHeader(value = "Authorization") final String token, @PathVariable final Long appUserId) {

        final AppUserResponse response = appUserService.getUserInfo(appUserId);

        return ResponseEntity.ok().body(response);
    }
}
