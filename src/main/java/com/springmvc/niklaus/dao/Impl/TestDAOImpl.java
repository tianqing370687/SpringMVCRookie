package com.springmvc.niklaus.dao.Impl;

import com.springmvc.niklaus.dao.TestDAO;
import com.springmvc.niklaus.pojo.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by nicholas.chi on 2016/9/23.
 */
@Repository
public class TestDAOImpl implements TestDAO{

    private SessionFactory sessionFactory;

    //构造DAO
    @Autowired
    public TestDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addTest(Test test) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(test);
        tx.commit();
        session.close();
    }

    public Test getTestById(long id) {
        Session session = sessionFactory.openSession();
        Test test = (Test) session.get(Test.class,id);
        session.close();
        return test;
    }

    public void updateTest(Test test) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(test);
        tx.commit();
        session.close();
    }


}
