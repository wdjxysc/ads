package com.jrcx.ads.domain;

import com.jrcx.ads.utils.AdsTool;

import javax.persistence.Column;
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

    private Date beginDate;

    private Date endDate;

    public Programme() {
    }

    public Programme(String name, Date beginDate, Date endDate) {
        this.sn = AdsTool.getSn();
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
