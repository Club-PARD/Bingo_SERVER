package com.threefour.bingo.auth.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.appUser.entity.AppUser;
import com.threefour.bingo.appUser.repository.AppUserRepository;
import com.threefour.bingo.auth.dto.SignInResponse;
import com.threefour.bingo.exception.UserResourceException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j

public class AuthService {
    private final Environment env;
    private final WebClient webClient;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AppUserRepository appUserRepository;

    public AuthService(Environment env, WebClient.Builder webClientBuilder) {
        this.env = env;
        this.webClient = webClientBuilder.build();
    }

    public SignInResponse signIn(String code, String registrationId) {
        String accessToken = getAccessToken(code, registrationId);

        JsonNode userResource = getUserResource(accessToken, registrationId).block();

        if (userResource == null) {
            throw new UserResourceException("Failed to get user resource");
        }

        String email = userResource.get("email").asText();
        String nickname = userResource.get("name").asText();

        AppUser appUser = appUserRepository.findByEmail(email);

        if (appUser == null) {
            AppUser newUser = new AppUser(nickname, email);
            appUserRepository.save(newUser);
            appUser = newUser;
            log.info("New user created with email: " + email);
        } else {
            log.info("Existing user logged in with email: " + email);
        }

        int jwtExpiration = 3600000;

        String jwtToken = tokenProvider.create(accessToken);

        SignInResponse response = new SignInResponse(jwtToken, jwtExpiration, appUser);

        return response;
    }


    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");
        log.info("client-id: " + clientId);
        log.info("client-secret: " + clientSecret);
        log.info("redirectUri: " + redirectUri);
        log.info("tokenUri: " + tokenUri);


        String accessToken = webClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("code", authorizationCode)
                        .with("client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("redirect_uri", redirectUri)
                        .with("grant_type", "authorization_code"))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> jsonNode.get("access_token").asText())
                .block();

        log.info("accessToken: " + accessToken);

        return accessToken;
    }

    private Mono<JsonNode> getUserResource(String accessToken, String registrationId) {
        String resourceUri = env.getProperty("oauth2." + registrationId + ".resource-uri");

        log.info("accessToken: " + accessToken);
        return webClient.get()
                .uri(resourceUri)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

}
