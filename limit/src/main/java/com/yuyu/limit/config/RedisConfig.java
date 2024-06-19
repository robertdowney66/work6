package com.yuyu.limit.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    /**
     * 加载lua脚本
     * @return 获取lua脚本后的redisScript
     */
    @Bean
    public DefaultRedisScript loadRedisScript(){
        DefaultRedisScript redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource("lua/limit.lua"));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }
    /**
     * 自定义了redisTemplate，使得数据能以json格式存储
     * @param connectionFactory
     * @return 能够按照所需格式序列化的redisTemplate
     */
    @Bean
    @SuppressWarnings(value = {"unchecked","rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
