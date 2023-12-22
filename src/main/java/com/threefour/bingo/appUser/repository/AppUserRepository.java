package com.threefour.bingo.appUser.repository;

import com.threefour.bingo.appUser.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    //로그인 한 사용자 회원가입 여부 판단
    Optional<AppUser> findByEmail(String email);
}
