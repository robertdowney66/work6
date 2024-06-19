package com.yuyu.common.interceptors;

import cn.hutool.core.util.StrUtil;

import com.yuyu.common.Utils.UserContext;
import com.yuyu.common.domain.Result;
import com.yuyu.common.exception.BussinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现用户信息的保存
 */
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取用"户登录信息
        String userInfo = request.getHeader("user-info");
        // 2.判断是否获取了用户，如果有，存入ThreadLocal
        if(StringUtils.hasText(userInfo)){
            UserContext.setUser(Long.valueOf(userInfo));
            log.info("用户id是"+userInfo);
        }
        // 3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户
        UserContext.removeUser();
        log.info("用户已清理");
    }
}
