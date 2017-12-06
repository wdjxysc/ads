package com.jrcx.ads.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
@Entity
public class User extends BaseEntity {

    @Column(unique = true)
    private String name;

    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
