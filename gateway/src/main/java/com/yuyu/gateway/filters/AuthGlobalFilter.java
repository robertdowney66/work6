package com.yuyu.gateway.filters;

import cn.hutool.core.text.AntPathMatcher;
import com.yuyu.common.domain.Result;
import com.yuyu.common.exception.BussinessException;
import com.yuyu.gateway.config.AuthProperties;
import com.yuyu.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 该拦截器用于登录校验的工作
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    
    private final AuthProperties authProperties;
    private final JwtTool jwtTool;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取request对象
        ServerHttpRequest request = exchange.getRequest();
        // 2.判断是否需要做登录拦截
        if (isExclude(request.getPath().toString())){
            // 放行
            return chain.filter(exchange);
        }
        // 3.获取token
        String token = null;
        List<String> headers = request.getHeaders().get("authorization");
        if(headers != null&& !headers.isEmpty()){
            token = headers.get(0);
        }
        // 4.校验并解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (Exception e) {
            // TODO
            throw new BussinessException(Result.PROJECT_BUSSINESS_ERROR,e.getMessage());
        }
        // 5.传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();
        // 6.放行
        return chain.filter(swe);
    }

    private boolean isExclude(String path) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(pathPattern,path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
