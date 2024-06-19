package com.yuyu.user;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableFeignClients(basePackages = "com.hmall.api.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.yuyu.user.mapper")
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.yuyu.user.**","com.yuyu.common.**","com.yuyu.limit.**"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}