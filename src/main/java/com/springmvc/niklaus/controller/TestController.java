package com.springmvc.niklaus.controller;

import com.springmvc.niklaus.pojo.Test;
import com.springmvc.niklaus.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        Test test = new Test();
        test.setId(1);
        test.setUserName("Klaus");
        test.setEmail("klaus@123.com");
        test.setPassword("123123123");
        test.setLastLogin(new Date());
        test.setDateJoined(new Date());

        testService.rSave("test1",test);

        return "index";
    }

    @RequestMapping(value="rtest",method = RequestMethod.GET)
    public String rtest(){
        Test test = testService.rGet("test1");
        log.info("test.ino : {}",test.toString());
        return "index";
    }

}
