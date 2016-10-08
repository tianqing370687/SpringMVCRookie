package com.springmvc.niklaus.controller;

import com.springmvc.niklaus.pojo.Test;
import com.springmvc.niklaus.redis.RedisManager;
import com.springmvc.niklaus.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nicholas.chi on 2016/9/23.
 */
@Controller
public class TestController {

    @Autowired
    TestService testService;

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value="test",method = RequestMethod.GET)
    public String test(){

        testService.rSave("1111","22222");
        return "index";
    }

    @RequestMapping(value="rtest",method = RequestMethod.GET)
    public String rtest(){
        String value = testService.rGet("1111");
        log.info("the value {}",value);
        return "index";
    }

}
