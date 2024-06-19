package com.yuyu.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 该类存储配置中的、是否需要拦截路径的信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "yuyu.auth")
public class AuthProperties {
    private List<String> includePaths;
    private List<String> excludePaths;
}
