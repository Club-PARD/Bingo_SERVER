package com.threefour.bingo.appUser.repository;

import com.threefour.bingo.appUser.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);

    AppUser findByName(String name);
}
