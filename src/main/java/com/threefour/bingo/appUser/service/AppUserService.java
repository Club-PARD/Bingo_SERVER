package com.threefour.bingo.appUser.service;

import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.test.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public ResponseDto<AppUserInfoResponse> getUserInfo(Long id) {

        Optional<AppUser> appUser = appUserRepository.findById(id);

        if (appUser.isEmpty()) {
            return ResponseDto.setFailed("User Not Found");
        }

        AppUserInfoResponse response = new AppUserInfoResponse(appUser.get().getName(), appUser.get().getEmail());

        return ResponseDto.setSuccess("User Info: ", response);
    }
}
