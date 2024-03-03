package com.threefour.bingo.auth.service;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.appUser.dto.response.AppUserResponse;
import com.threefour.bingo.appUser.service.AppUserSubService;
import com.threefour.bingo.auth.dto.request.SignInRequest;
import com.threefour.bingo.auth.dto.response.SignInResponse;
import com.threefour.bingo.auth.dto.response.SignOutResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {


    private final TokenProvider tokenProvider;
    private final AppUserRepository appUserRepository;
    private final AppUserSubService appUserSubService;

    // 로그인 메소드
    @Transactional
    public SignInResponse signIn(SignInRequest request) {

        appUserSubService.isEmailVerified(request.isEmailVerified());

        int isSigned = 2;

        AppUser appUser = appUserSubService.findAppUserByEmail(request.getEmail());
        String token = tokenProvider.create(request.getEmail());
        int exprTime = 1000 * 3600 * 3;

        if (appUser == null) {
            isSigned = 1;
            appUser = new AppUser(request.getName(), request.getEmail(), request.getPicture(), token);
        }
        appUser.update(token);
        appUserRepository.save(appUser);

        AppUserResponse appUserResponse = new AppUserResponse(appUser);

        return new SignInResponse(appUserResponse, isSigned, token, exprTime);
    }

    // 로그아웃 메소드
    @Transactional
    public SignOutResponse signOut(Long appUserId) {

        AppUser appUser = appUserSubService.findAppUserById(appUserId);

        appUser.update(null);
        appUserRepository.save(appUser);

        return new SignOutResponse(appUser.getEmail());
    }
}
