package com.threefour.bingo.appUser.controller;

import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import com.threefour.bingo.appUser.service.AppUserService;
import com.threefour.bingo.test.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appUser")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/{id}")
    public ResponseDto<AppUserInfoResponse> getUserInfo(@PathVariable final Long id) {

        ResponseDto<AppUserInfoResponse> response = appUserService.getUserInfo(id);

        return response;
    }
}
