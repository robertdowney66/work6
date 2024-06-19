package com.yuyu.user.service;

import com.yuyu.common.domain.Result;
import com.yuyu.user.domain.po.User;

public interface UserService {

    /**
     * 用户登录
     * @param name 用户账号
     * @param password 用户密码
     * @return 操作结果
     */
    Result<User> login(String name, String password);

    /**
     * 用户注册
     * @param name 用户账号
     * @param password 用户密码
     * @return 操作结果
     */
    Result register(String name,String password);
}
