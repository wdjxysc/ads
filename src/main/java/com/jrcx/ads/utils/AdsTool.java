package com.jrcx.ads.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator.
 * @date 2017/11/23
 */
public class AdsTool {


    public static String getSn(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        simpleDateFormat.format(new Date());

        String sn = simpleDateFormat.format(new Date()) + "-" +  UUID.randomUUID().toString();

        return sn;
    }
}
