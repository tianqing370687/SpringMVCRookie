package com.springmvc.niklaus.service;

import com.springmvc.niklaus.pojo.Test;

/**
 * Created by nicholas.chi on 2016/9/23.
 */
public interface TestService {

    public void addTest(Test test);

    public Test getTestById(long id);

    public void rSave(String key,String value);

    public String rGet(String key);

}
