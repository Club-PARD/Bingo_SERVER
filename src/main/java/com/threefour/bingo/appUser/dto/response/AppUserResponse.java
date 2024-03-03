package com.threefour.bingo.appUser.dto.response;

import com.threefour.bingo.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {

    private Long id;

    private String name;

    private String email;

    private String picture;

    public AppUserResponse(AppUser appUser){
        this.id = appUser.getId();
        this.name = appUser.getName();
        this.email = appUser.getEmail();
        this.picture = appUser.getPicture();
    }

}
