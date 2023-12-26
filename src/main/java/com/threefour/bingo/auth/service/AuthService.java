package com.threefour.bingo.auth.service;

import com.threefour.bingo.appUser.entity.AppUser;
import com.threefour.bingo.appUser.repository.AppUserRepository;
import com.threefour.bingo.auth.dto.request.SignInRequest;
import com.threefour.bingo.auth.dto.response.SignInResponse;
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

//    @Transactional
//    public SignInResponse signIn(SignInRequest request) {
//        AppUser appUser = appUserRepository.findByEmail(request.getEmail());
//
//        if (appUser == null) {
//            AppUser newUser = new AppUser(request.getName(), request.getEmail());
//            appUserRepository.save(newUser);
//        }
//
////        int exprTime = ;
//        String token = tokenProvider.create(request.getEmail());
//    }
}
