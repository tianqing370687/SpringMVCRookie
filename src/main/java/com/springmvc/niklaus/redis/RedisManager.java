package com.springmvc.niklaus.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by nicholas.chi on 2016/10/3.
 */
@Component
public class RedisManager {

    @Resource
    RedisTemplate redisTemplate;

    public void save(Object key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }
}
