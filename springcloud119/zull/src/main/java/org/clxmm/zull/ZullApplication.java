package org.clxmm.zull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy //开启网关代理
public class ZullApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZullApplication.class, args);
    }

}
