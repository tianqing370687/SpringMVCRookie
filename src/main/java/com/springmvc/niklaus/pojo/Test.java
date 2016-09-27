package com.springmvc.niklaus.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by nicholas.chi on 2016/9/23.
 */
@Entity
@Table(name = "auth_user")
public class Test {
    private long id;
    private String userName;
    private String email;
    private String password;
    private Date lastLogin;
    private Date dateJoined;
    @Id
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "username")
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "last_login")
    public Date getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    @Column(name = "date_joined")
    public Date getDateJoined() {
        return dateJoined;
    }
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastLogin=" + lastLogin +
                ", dateJoined=" + dateJoined +
                '}';
    }
}
