package com.jrcx.ads.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/12/5
 */
@Entity
public class Publish extends BaseEntity {

    private Long programmeId;

    private Long deviceId;

    private Date beginDate;

    private Date endDate;

    public Publish(){

    }

    public Long getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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
