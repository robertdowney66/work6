package com.yuyu.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.time.Duration;

/**
 * 该类用于存储配置中的有关jwt的信息
 */
@Data
@ConfigurationProperties(prefix = "yuyu.jwt")
public class JwtProperties {
    private Resource location;
    private String password;
    private String alias;
    private Duration tokenTTL = Duration.ofMinutes(10);
}
