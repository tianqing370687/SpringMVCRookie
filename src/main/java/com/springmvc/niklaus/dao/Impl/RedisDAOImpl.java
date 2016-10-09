package com.springmvc.niklaus.dao.Impl;

import com.springmvc.niklaus.dao.RedisDAO;
import com.springmvc.niklaus.utils.json.JsonBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by nicholas.chi on 2016/10/9.
 */
@Repository
public class RedisDAOImpl implements RedisDAO {

    @Resource
    private RedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisDAOImpl.class);

    public void save(String key, Object value) {
        if(value instanceof String){
            redisTemplate.opsForValue().set(key,value);
        }else{
            String valueStr = JsonBinder.getInstance().toJson(value);
            redisTemplate.opsForValue().set(key,valueStr);
        }
    }

    public String getValueByKey(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }
}
