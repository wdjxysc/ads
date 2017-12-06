package com.jrcx.ads.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
@Entity
public class Programme extends BaseEntity {

    private String name;

    private String sn;

    /**
     * 状态 是否发布
     */
    private Integer state;

    public Programme() {
    }

    public Programme(String name) {
        this.name = name;
        this.setCreateDate(new Date());
        this.setUpdateDate(new Date());
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
