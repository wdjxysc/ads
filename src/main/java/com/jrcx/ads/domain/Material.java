package com.jrcx.ads.domain;

import com.jrcx.ads.emuns.MaterialType;
import com.jrcx.ads.utils.AdsTool;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
@Entity
public class Material extends BaseEntity {

    @Column
    private String sn;

    @Column
    private String name;

    @Column
    private String path;

    @Column
    private String title;

    @Column
    private Integer type;

    public Material() {

    }

    public Material(String name, String path, String title, Integer type) {
        this.sn = AdsTool.getSn();
        this.name = name;
        this.path = path;
        this.title = title;
        this.type = type;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

