package com.springmvc.niklaus.dao.Impl;

import com.springmvc.niklaus.dao.TestDAO;
import com.springmvc.niklaus.pojo.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by nicholas.chi on 2016/9/23.
 */
@Repository
public class TestDAOImpl implements TestDAO{

    @Resource
    HibernateTemplate hibernateTemplate;

    public void addTest(Test test) {
        hibernateTemplate.save(test);
    }

    public Test getTestById(long id) {
        return hibernateTemplate.get(Test.class,id);
    }

    public void updateTest(Test test) {
        hibernateTemplate.saveOrUpdate(test);
    }
}
