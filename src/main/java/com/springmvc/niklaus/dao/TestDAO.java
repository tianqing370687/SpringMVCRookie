package com.springmvc.niklaus.dao;

import com.springmvc.niklaus.pojo.Test;

/**
 * Created by nicholas.chi on 2016/9/23.
 */
public interface TestDAO {

    public void addTest(Test test);

    public Test getTestById(long id);

    public void updateTest(Test test);

    public void rSave(String key,String value);

    public String rGet(String key);

}
