package com.yuyu.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyu.common.exception.BussinessException;
import com.yuyu.common.exception.UnauthorizedException;
import com.yuyu.user.config.JwtProperties;
import com.yuyu.common.domain.Result;
import com.yuyu.user.utils.JwtTool;
import com.yuyu.user.domain.po.User;
import com.yuyu.user.mapper.UserMapper;
import com.yuyu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User>implements UserService {


    private final PasswordEncoder passwordEncoder;
    private final JwtTool jwtTool;
    private final JwtProperties jwtProperties;

    @Override
    public Result<User> login(String name, String password) {
        // 1.根据用户名查询
        User user = lambdaQuery().eq(User::getName,name).one();
        if(Objects.isNull(user)){
            throw new UnauthorizedException(Result.PROJECT_BUSSINESS_ERROR,"用户不存在");
        }
        // 2.校验密码
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new UnauthorizedException(Result.PROJECT_BUSSINESS_ERROR,"密码错误");
        }
        // 3.生成TOKEN
        String token = jwtTool.createToken(Long.valueOf(user.getId()),jwtProperties.getTokenTTL());

        Map map = new HashMap();
        map.put("token",token);
        map.put("user",user);
        // 4.将用户信息返回
        return new Result(Result.PROJECT_SUCCESS,"success",map);
    }

    @Override
    public Result register(String name, String password) {
        // 1.检查用户名,密码是否为空
        if(!StringUtils.hasText(name)||!StringUtils.hasText(password)){
            throw new BussinessException(Result.PROJECT_BUSSINESS_ERROR,"密码不能为空");
        }
        // 2.检查用户名是否已经存在
        User user = lambdaQuery().eq(User::getName,name).one();
        if (!Objects.isNull(user)){
            throw new BussinessException(Result.PROJECT_BUSSINESS_ERROR,"用户已存在");
        }
        // 3.存入数据库内
        User newUser = new User(name,passwordEncoder.encode(password), LocalDateTime.now());
        baseMapper.insert(newUser);
        return new Result(Result.PROJECT_SUCCESS,"success");
    }
}
