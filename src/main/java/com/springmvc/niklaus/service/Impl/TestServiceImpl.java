package com.springmvc.niklaus.service.Impl;

import com.springmvc.niklaus.dao.RedisDAO;
import com.springmvc.niklaus.dao.TestDAO;
import com.springmvc.niklaus.pojo.Test;
import com.springmvc.niklaus.service.TestService;
import com.springmvc.niklaus.utils.json.JsonBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nicholas.chi on 2016/9/23.
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestDAO testDAO;
    @Autowired
    RedisDAO redisDAO;

    public void addTest(Test test) {
        testDAO.addTest(test);
    }

    public Test getTestById(long id) {
        return testDAO.getTestById(id);
    }

    public void rSave(String key,Test value){
        redisDAO.save(key,value);
    }

    public Test rGet(String key){
        String value = redisDAO.getValueByKey(key);
        return JsonBinder.getInstance().fromJson(value,Test.class);
    }
}
