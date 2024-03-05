package com.threefour.bingo.configuration;

import com.threefour.bingo.auth.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        // CORS 설정을 활성화하고, 기본 설정을 사용
        httpSecurity.cors(Customizer.withDefaults());

        // CSRF 보호 기능을 비활성화
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // 기본 HTTP 인증을 비활성화
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);

//        // 요청 URL에 따른 접근 권한 설정: "/swagger-ui/index.html", "/signUp", "/signIn" 경로로 들어오는 요청은 허용, 그 외의 요청은 인증을 필요로 함
        httpSecurity.authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
//                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/api/v1/auth/*")
                                .requestMatchers("/**")
                                .permitAll()
//                        .requestMatchers("/*")
//                        .permitAll()
                                .anyRequest()
                                .authenticated()
        );
//        httpSecurity.authorizeHttpRequests(authorizeHttpRequests ->
//                authorizeHttpRequests.anyRequest().permitAll()
//        );


        // JWT를 이용한 인증 처리를 수행하는 필터를 추가
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 설정한 보안 설정을 빌드하여 반환
        return httpSecurity.build();
    }
}