package com.yuyu.transaction;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableFeignClients(basePackages = "com.yuyu.transcation",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.yuyu.transaction.mapper")
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.yuyu.transaction.**","com.yuyu.common.**","com.yuyu.limit.**"})
public class TransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

}