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

    private String deviceName;

    private String sn;

    private String owner;

    public Device() {

    }

    public Device(String deviceName, String sn) {
        this.deviceName = deviceName;
        this.sn = sn;
        this.setCreateDate(new Date());
        this.setUpdateDate(new Date());
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
