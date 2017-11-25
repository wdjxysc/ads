package com.jrcx.ads.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
@Entity
public class Device extends BaseEntity{

    @Column
    private String name;

    @Column
    private String sn;

    public Device() {

    }

    public Device(String name, String sn) {
        this.name = name;
        this.sn = sn;
        this.setCreateDate(new Date());
        this.setUpdateDate(new Date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
