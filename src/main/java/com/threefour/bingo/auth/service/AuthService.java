package com.threefour.bingo.auth.service;

import com.threefour.bingo.appUser.entity.AppUser;
import com.threefour.bingo.appUser.repository.AppUserRepository;
import com.threefour.bingo.auth.dto.request.SignInRequest;
import com.threefour.bingo.auth.dto.request.SignOutRequest;
import com.threefour.bingo.auth.dto.response.SignInResponse;
import com.threefour.bingo.auth.dto.response.SignOutResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class AuthService {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AppUserRepository appUserRepository;

    @Transactional
    public SignInResponse signIn(SignInRequest request) {
        AppUser appUser = appUserRepository.findByEmail(request.getEmail());

        if (appUser == null) {
            AppUser newUser = new AppUser(request.getName(), request.getEmail());
            appUser = newUser;
        }

        int exprTime = 1000 * 3600;
        String token = tokenProvider.create(request.getEmail());
        appUser.setToken(token);

        appUserRepository.save(appUser);

        SignInResponse response = new SignInResponse(token, exprTime, appUser);

        return response;
    }

    @Transactional
    public SignOutResponse signOut(SignOutRequest request) {
        AppUser appUser = appUserRepository.findByEmail(request.getEmail());

        if (appUser == null) {
            log.info("등록되지 않은 사용자입니다.");
        }

        appUser.setToken(null);

        SignOutResponse response = new SignOutResponse(request.getEmail());

        return response;
    }
}
