package com.dataart.maltahackaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MaltaHackatonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaltaHackatonApplication.class, args);
    }
}
