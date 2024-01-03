package com.threefour.bingo.appUser.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserInfoResponse getUserInfo(Long id) {

        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        AppUserInfoResponse response = new AppUserInfoResponse(appUser.getId(), appUser.getName(), appUser.getEmail(), appUser.getPicture());

        return response;
    }
}
