package org.clxmm.hystrix01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/*
@SpringBootApplication
@EnableCircuitBreaker*/
@SpringCloudApplication
public class Hystrix01Application {

    public static void main(String[] args) {
        SpringApplication.run(Hystrix01Application.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
