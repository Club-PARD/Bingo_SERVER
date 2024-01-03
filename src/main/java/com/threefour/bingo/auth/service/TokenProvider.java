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
    private static final Key SECURITY_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String create(String email) {
        Date exprTime = Date.from(Instant.now().plus(3, ChronoUnit.HOURS));

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .setSubject(email).setIssuedAt((new Date())).setExpiration(exprTime)
                .compact();
    }

    public String validate(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECURITY_KEY).build().parseClaimsJws(token).getBody();

        log.info("claims: " + claims.getSubject());

        return claims.getSubject();
    }
}