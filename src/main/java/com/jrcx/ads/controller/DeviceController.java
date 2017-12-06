package com.jrcx.ads.controller;

import com.jrcx.ads.domain.Device;
import com.jrcx.ads.domain.dao.DeviceRepository;
import com.jrcx.ads.res.BaseRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/12/5
 */
@Controller
@RequestMapping(value = "api/device")
public class DeviceController extends BaseController {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public BaseRes login(@RequestParam String sn, @RequestParam String username, @RequestParam String deviceName){
        BaseRes baseRes = new BaseRes();

        Device device = deviceRepository.findDeviceBySn(sn);

        if(device!=null){
            deviceRepository.update(new Date(),deviceName,username,device.getSn());
        }else {
            device = new Device();
            device.setSn(sn);
            device.setOwner(username);
            device.setDeviceName(deviceName);
            device.setCreateDate(new Date());
            device.setCreateBy(username);
            device.setUpdateDate(new Date());
            deviceRepository.save(device);
        }

        return baseRes;
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@RequestParam Long id){
        BaseRes baseRes = new BaseRes();

        deviceRepository.delete(id);

        return baseRes;
    }

}
