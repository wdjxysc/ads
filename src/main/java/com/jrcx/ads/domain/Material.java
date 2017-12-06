package com.jrcx.ads.domain;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
@Entity
public class Material extends BaseEntity {

    private String name;

    private String path;

    private Integer type;

    public Material() {

    }

    public Material(String name, String path, Integer type) {
        this.name = name;
        this.path = path;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

