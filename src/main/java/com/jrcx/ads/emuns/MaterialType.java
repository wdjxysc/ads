package com.jrcx.ads.emuns;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Administrator.
 * @date 2017/11/23
 */
public enum MaterialType {

    TYPE_IMG(1,"图片"),
    TYPE_VEDIO(2,"视频");


    MaterialType(Integer key,String value){
        this.key = key;
        this.value = value;
    }

    public Integer key;
    public String value;
}
