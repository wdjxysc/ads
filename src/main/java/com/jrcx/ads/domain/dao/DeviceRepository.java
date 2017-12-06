package com.jrcx.ads.domain.dao;

import com.jrcx.ads.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device findByDeviceName(String deviceName);

    Device findByCreateDateAfter(Date createDate);

    Device findDeviceBySn(String sn);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update Device d set d.updateDate=:updateDate,d.owner=:owner,d.deviceName=:deviceName where d.sn=:sn")
    int update(@Param("updateDate") Date updateDate, @Param("deviceName") String deviceName, @Param("owner") String owner, @Param("sn") String sn);
}
