package com.threefour.bingo.auth.service;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.auth.dto.request.SignInRequest;
import com.threefour.bingo.auth.dto.request.SignOutRequest;
import com.threefour.bingo.auth.dto.response.SignInResponse;
import com.threefour.bingo.auth.dto.response.SignOutResponse;
import com.threefour.bingo.test.ResponseDto;
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
    public ResponseDto<SignInResponse> signIn(SignInRequest request) {

        AppUser appUser = appUserRepository.findByEmail(request.getEmail());

        if (appUser == null) { // 유저가 존재하지 않는 경우
            return ResponseDto.setFailed("User NotFound");
        }

        if (!request.isEmailVerified()) { // 이메일이 인증되지 않은 경우
            return ResponseDto.setFailed("Unverified Account");
        }

        int exprTime = 1000 * 3600;
        String token = tokenProvider.create(request.getEmail());
        AppUser newUser = new AppUser(request.getName(), request.getEmail(), request.getToken());

        appUserRepository.save(newUser);

        SignInResponse response = new SignInResponse(token, exprTime, newUser);

        return ResponseDto.setSuccess("SignIn Success", response);
    }

    @Transactional
    public ResponseDto<SignOutResponse> signOut(SignOutRequest request) {

        AppUser appUser = appUserRepository.findByEmail(request.getEmail());

        if (appUser == null) { // 사용자가 없는 경우
            return ResponseDto.setFailed("User Notfound");
        }

        if (appUser.getToken() == null) { // 토큰이 null 인 경우
            return ResponseDto.setFailed("Token Expired");
        }

        SignOutResponse response = new SignOutResponse(request.getEmail());

        return ResponseDto.setSuccess("Success SignOut", response);
    }
}
