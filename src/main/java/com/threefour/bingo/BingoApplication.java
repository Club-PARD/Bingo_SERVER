package com.threefour.bingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BingoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BingoApplication.class, args);
    }

}
