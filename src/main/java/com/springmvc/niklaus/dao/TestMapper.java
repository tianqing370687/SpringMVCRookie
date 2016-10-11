package com.springmvc.niklaus.dao;

import com.springmvc.niklaus.pojo.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by nicholas.chi on 2016/10/9.
 */
public interface TestMapper {

    @Select("SELECT * From auth_user WHERE id = #{id}")
    Test getTest(@Param("id") long id);

    @Insert("insert into auth_user(username,email,password,last_login,date_joined) values (#{userName},#{email},#{password},#{lastLogin},#{dateJoined}) ")
    void saveTest(Test test);

}
