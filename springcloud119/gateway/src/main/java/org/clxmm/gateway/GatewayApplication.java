package org.clxmm.gateway;

import ch.qos.logback.classic.joran.action.RootLoggerAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /*@Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return  builder.routes()
                .route("clxmm_route",r -> r.path("/get").uri("http://httpbin.org"))
                .build();
    }*/
}
