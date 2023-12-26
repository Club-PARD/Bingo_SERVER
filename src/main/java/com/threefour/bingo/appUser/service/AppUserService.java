package com.threefour.bingo.appUser.service;

import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import com.threefour.bingo.appUser.entity.AppUser;
import com.threefour.bingo.appUser.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserInfoResponse getUserInfo(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        AppUserInfoResponse response = new AppUserInfoResponse(appUser.getName(), appUser.getEmail());

        return response;
    }
}
