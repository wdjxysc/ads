package com.jrcx.ads.domain;

import javax.persistence.Entity;

/**
 * @author Administrator.
 * @date 2017/11/25
 */
@Entity
public class Apk extends BaseEntity {
    private String name;
    private Integer versionNum;
    private String path;

    public Apk() {
    }

    public Apk(String name, Integer versionNum, String path) {
        this.name = name;
        this.versionNum = versionNum;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
