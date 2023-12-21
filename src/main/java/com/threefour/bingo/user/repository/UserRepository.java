package com.threefour.bingo.user.repository;

import com.threefour.bingo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //로그인 한 사용자 회원가입 여부 판단
    Optional<User> findByEmail(String email);
}
