package com.jrcx.ads.domain;

import com.jrcx.ads.utils.AdsTool;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Administrator.
 * @date 2017/11/23
 */
@Entity
public class PieceProgramme extends BaseEntity {
    @Column
    private String sn;

    @Column
    private String programmeSn;

    @Column
    private String materialSn;

    @Column
    private Integer timeSecond;

    @Column
    private Integer indexNum;

    public PieceProgramme(){

    }

    public PieceProgramme(String programmeSn, String materialSn, Integer timeSecond, Integer indexNum) {
        this.sn = AdsTool.getSn();
        this.programmeSn = programmeSn;
        this.materialSn = materialSn;
        this.timeSecond = timeSecond;
        this.indexNum = indexNum;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getProgrammeSn() {
        return programmeSn;
    }

    public void setProgrammeSn(String programmeSn) {
        this.programmeSn = programmeSn;
    }

    public String getMaterialSn() {
        return materialSn;
    }

    public void setMaterialSn(String materialSn) {
        this.materialSn = materialSn;
    }

    public Integer getTimeSecond() {
        return timeSecond;
    }

    public void setTimeSecond(Integer timeSecond) {
        this.timeSecond = timeSecond;
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
    }
}
