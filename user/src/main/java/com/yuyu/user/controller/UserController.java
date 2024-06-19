package com.yuyu.user.controller;


import com.yuyu.common.domain.Result;
import com.yuyu.limit.annotation.RedisLimiter;
import com.yuyu.limit.enums.Unit;
import com.yuyu.user.domain.po.User;
import com.yuyu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 测试限流所用的测试接口
     * @return 测试的结果
     */
    @GetMapping("/test")
    @RedisLimiter(maxLimit = 10,key = "测试接口",range = 1,unit = Unit.MINUTE)
    public Result testLimit(){
        return new Result(Result.PROJECT_SUCCESS,"hhh");
    }

    /**
     * 用户登录接口
     * @param name 用户账号
     * @param password 用户密码
     * @return 操作结果
     */
    @PostMapping("/login")
    @RedisLimiter(maxLimit = 10,key = "测试接口",range = 2,unit = Unit.SECOND)
    public Result<User> login(@RequestParam("username") String name,@RequestParam("password") String password){
        return userService.login(name,password);
    }

    /**
     * 用户注册的接口
     * @param name 用户账号
     * @param password 用户密码
     * @return 操作结果
     */
    @PostMapping("/register")
    @RedisLimiter(maxLimit = 10,key = "测试接口",range = 2,unit = Unit.SECOND)
    public Result register(@RequestParam("username") String name,@RequestParam("password") String password){
        return userService.register(name,password);
    }
}
