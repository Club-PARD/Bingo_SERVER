package com.threefour.bingo.appUser.service;

import com.threefour.bingo.appUser.dto.response.AppUserResponse;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserSubService appUserSubService;

    // 사용자 id로 사용자 정보를 조회 메소드
    public AppUserResponse getUserInfo(Long appUserId) {

        AppUser appUser = appUserSubService.findAppUserById(appUserId);

        return new AppUserResponse(appUser);
    }
}
