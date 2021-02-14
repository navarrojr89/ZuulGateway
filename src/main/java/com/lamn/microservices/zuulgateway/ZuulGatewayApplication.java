package com.lamn.microservices.zuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * The type Zuul gateway application.
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }

}

