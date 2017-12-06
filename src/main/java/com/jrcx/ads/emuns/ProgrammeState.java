package com.jrcx.ads.emuns;

/**
 * @author Administrator.
 * @date 2017/12/5
 */
public enum ProgrammeState {

    PUBLISHED(1,"已发布"),
    UNPUBLISHED(0,"未发布");

    ProgrammeState(Integer key, String value){

    }

    public Integer key;
    public String value;
}
