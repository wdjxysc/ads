package com.jrcx.ads.domain;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
@MappedSuperclass
public abstract class CreateEntity extends KeyEntity{
    @Column
    private Date createDate = new Date();
    @Column
    private String createBy;


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
