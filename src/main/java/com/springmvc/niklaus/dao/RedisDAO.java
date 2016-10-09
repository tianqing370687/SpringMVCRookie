package com.springmvc.niklaus.dao;

/**
 * Created by nicholas.chi on 2016/10/9.
 */
public interface RedisDAO {

    public void save(String key,Object value);

    public String getValueByKey(String key);

}
