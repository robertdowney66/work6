package com.yuyu.limit.aspect;


import com.yuyu.common.domain.Result;
import com.yuyu.common.exception.BussinessException;
import com.yuyu.limit.annotation.RedisLimiter;
import com.yuyu.limit.enums.Unit;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@Aspect
@Component
@Slf4j
public class RedisLimiterAspect {

    /**
     * 执行lua的脚本文件
     */
    @Autowired
    private RedisScript<Boolean> redisScript;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Before("@annotation(redisLimiter)")
    public void doBefore(JoinPoint joinPoint, RedisLimiter redisLimiter){
        System.out.println(System.currentTimeMillis());
        // 1.获取注解中的标识和限流数目
        String key = redisLimiter.key();
        int maxLimit = redisLimiter.maxLimit();
        int range = redisLimiter.range();
        Unit unit = redisLimiter.unit();

        // 2.List设置lua中的KEYS[1]
        List<String> keylist = new ArrayList<>();
        keylist.add(combineKey(redisLimiter,joinPoint));

        // 3.调用redis执行lua脚本，未拿到令牌，返回提示
        try {
            boolean acquired = this.execute(keylist,maxLimit,range*judgeUnit(unit));
            log.info("执行lua结果："+ acquired);
            if (!acquired){
                // 说明被限流
                throw new BussinessException(Result.PROJECT_BUSSINESS_ERROR,"当前系统繁忙，请稍后再试");
            }
        } catch (BussinessException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
    }

    public String combineKey(RedisLimiter redisLimiter,JoinPoint joinPoint){
        // 1.获取注解中的key
        StringBuffer stringBuffer = new StringBuffer(redisLimiter.key());
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        // 2.获取此方法的方法和类，用于组合key
        Method method = signature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        // 3.key中添加方法名-类名
        stringBuffer.append(declaringClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }
    public Integer judgeUnit(Unit unit){
        if(unit == Unit.HOUR){
            return 3600;
        }else if(unit == Unit.MINUTE){
            return 60;
        }else {
            return 1;
        }
    }
    /**
     * 将redisTemplate执行lua脚本的操作抽离至此
     * @return 执行结果
     */
    public Boolean execute(List keylist,int value,int range){
        return redisTemplate.execute(redisScript, keylist, value, range);
    }
}
