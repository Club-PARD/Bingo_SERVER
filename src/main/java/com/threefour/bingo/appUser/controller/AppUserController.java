package com.threefour.bingo.appUser.controller;

import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import com.threefour.bingo.appUser.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appUser")
public class AppUserController {
    private final AppUserService appUserService;

    public ResponseEntity<AppUserInfoResponse> getUserInfo(@PathVariable Long id) {
        AppUserInfoResponse response = appUserService.getUserInfo(id);

        return ResponseEntity.ok()
                .body(response);
    }
}
