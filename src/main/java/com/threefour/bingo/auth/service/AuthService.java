package com.threefour.bingo.auth.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import com.threefour.bingo.auth.dto.request.SignInRequest;
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

    //왜 토큰 값이 똑같지
    @Transactional
    public SignInResponse signIn(SignInRequest request) {

        log.info("Email: " + request.getEmail());
        AppUser appUser = appUserRepository.findByEmail(request.getEmail());

        if (!request.isEmailVerified()) { // 이메일이 인증되지 않은 경우
            return null;
        }

        String token = tokenProvider.create(request.getEmail());
        int exprTime = 1000 * 3600 * 3;

        if (appUser == null) {
            log.info("new user");
            AppUser newUser = new AppUser(request.getName(), request.getEmail(), request.getPicture(), token);
            appUser = newUser;
        }

        appUser.update(token);

        appUserRepository.save(appUser);

        AppUser responseUser = new AppUser(appUser.getId(), appUser.getName(), appUser.getEmail(), appUser.getPicture(), appUser.getToken());
        AppUserInfoResponse appUserInfoResponse = new AppUserInfoResponse(appUser.getId(), appUser.getName(), appUser.getEmail(), appUser.getPicture());
        log.info("existing user");
        SignInResponse response = new SignInResponse(exprTime, appUserInfoResponse, token);

        return response;
    }

    @Transactional
    public SignOutResponse signOut(Long id) {

        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));


        if (appUser.getToken() == null) { // 토큰이 null 인 경우
            return null;
        }

        appUser.update(null);

        appUserRepository.save(appUser);

        SignOutResponse response = new SignOutResponse(appUser.getEmail());

        return response;
    }
}
