package com.jrcx.ads.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
@MappedSuperclass
public abstract class BaseEntity extends CreateEntity {
    @Column
    private Date updateDate = new Date();
    @Column
    private String updateBy;


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
