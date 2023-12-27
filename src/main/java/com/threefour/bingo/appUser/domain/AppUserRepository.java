package com.threefour.bingo.appUser.domain;

import com.threefour.bingo.appUser.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);

    AppUser findByName(String name);
}
