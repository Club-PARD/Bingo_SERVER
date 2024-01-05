package com.threefour.bingo.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Slf4j
public class TokenProvider {
    private static final Key SECURITY_KEY = Keys.hmacShaKeyFor(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());

    public String create(String email) {
        Date exprTime = Date.from(Instant.now().plus(3, ChronoUnit.HOURS));

        return Jwts.builder()
                .signWith(SECURITY_KEY, SignatureAlgorithm.HS512)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(exprTime)
                .compact();
    }

    public String validate(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();

        log.info("Token validation successful. Subject: {}", claims.getSubject());

        return claims.getSubject();
    }
}
