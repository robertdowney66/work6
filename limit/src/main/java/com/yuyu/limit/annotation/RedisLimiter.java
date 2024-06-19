package com.yuyu.limit.annotation;


import com.yuyu.limit.enums.Unit;

import java.lang.annotation.*;

/**
 * 流量控制类
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLimiter {
    /**
     * 限流的数量
     */
    int maxLimit() default 100;

    /**
     * 每个限流模块的唯一标识
     */
    String key() default "";

    /**
     * 限制时间的单位
     */
    Unit unit() default Unit.SECOND;

    /**
     * 限制的时间范围
     */
    int range() default 2;
}

