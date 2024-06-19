package com.yuyu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.yuyu.common.exception","com.yuyu.gateway.**"})
public class GatAwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatAwayApplication.class);
    }
}
