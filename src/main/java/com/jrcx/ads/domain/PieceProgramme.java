package com.jrcx.ads.domain;


import javax.persistence.Entity;

/**
 * @author Administrator.
 * @date 2017/11/23
 */
@Entity
public class PieceProgramme extends BaseEntity {

    private Long programmeId;

    private Long materialId;

    private Integer timeSecond;

    private Integer indexNum;

    public PieceProgramme(){

    }

    public PieceProgramme(Long programmeId, Long materialId, Integer timeSecond, Integer indexNum) {
        this.programmeId = programmeId;
        this.materialId = materialId;
        this.timeSecond = timeSecond;
        this.indexNum = indexNum;
    }

    public Long getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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
