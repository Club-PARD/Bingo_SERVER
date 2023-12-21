package com.threefour.bingo.auth.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LoginService {

    private final Environment env;
    private final WebClient webClient;

    public LoginService(Environment env, WebClient.Builder webClientBuilder) {
        this.env = env;
        this.webClient = webClientBuilder.build();
    }

    public void socialLogin(String code, String registrationId) {
        String accessToken = getAccessToken(code, registrationId);

        getUserResource(accessToken, registrationId).subscribe(userResourceNode -> {
            System.out.println("userResourceNode = " + userResourceNode);

            String id = userResourceNode.get("id").asText();
            String email = userResourceNode.get("email").asText();
            String nickname = userResourceNode.get("name").asText();

            System.out.println("access token = " + accessToken);
            System.out.println("id = " + id);
            System.out.println("email = " + email);
            System.out.println("nickname = " + nickname);
        });
    }

    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");

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

        return accessToken;
    }

    private Mono<JsonNode> getUserResource(String accessToken, String registrationId) {
        String resourceUri = env.getProperty("oauth2." + registrationId + ".resource-uri");

        return webClient.get()
                .uri(resourceUri)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

}
