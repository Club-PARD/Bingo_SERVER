package com.threefour.bingo.appUser.service;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserSubService {

    private final AppUserRepository appUserRepository;

    // 사용자 id로 레포지토리에서 사용자를 찾아 반환
    public AppUser findAppUserById(Long appUserId) {
        return appUserRepository.findById(appUserId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
    }

    // 사용자 이메일로 레포지토리에서 사용자를 찾아 반환
    public Optional<AppUser> findAppUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    // 사용자 이메일 인증여부 확인
    public void isEmailVerified(boolean emailVerified) {
        if (!emailVerified) {
            throw new IllegalArgumentException("Email is not verified");
        }
    }
}
