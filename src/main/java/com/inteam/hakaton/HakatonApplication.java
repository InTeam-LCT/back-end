package com.inteam.hakaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HakatonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HakatonApplication.class, args);
    }

}
