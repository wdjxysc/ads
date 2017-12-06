package com.jrcx.ads.controller;

import com.jrcx.ads.domain.*;
import com.jrcx.ads.domain.dao.*;
import com.jrcx.ads.emuns.ProgrammeState;
import com.jrcx.ads.res.BaseRes;
import com.jrcx.ads.res.DataRes;
import com.jrcx.ads.utils.AdsTool;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.jrcx.ads.interceptor.LoginCheckInterceptor.SESSION_KEY_USER;


/**
 * @author Administrator.
 * @date 2017/11/27
 */
@Controller
@RequestMapping(value = "/api/programme")
public class ProgrammeController extends BaseController {
    private final ProgrammeRepository programmeRepository;

    private final PieceProgrammeRepository pieceProgrammeRepository;

    private final PublishRepository publishRepository;

    private final DeviceRepository deviceRepository;

    private final MaterialRepository materialRepository;

    @Autowired
    public ProgrammeController(ProgrammeRepository programmeRepository, PieceProgrammeRepository pieceProgrammeRepository, PublishRepository publishRepository, DeviceRepository deviceRepository, MaterialRepository materialRepository) {
        this.programmeRepository = programmeRepository;
        this.pieceProgrammeRepository = pieceProgrammeRepository;
        this.publishRepository = publishRepository;
        this.deviceRepository = deviceRepository;
        this.materialRepository = materialRepository;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes add(@RequestBody JSONObject requestBody, HttpServletRequest request) {
        BaseRes res = new BaseRes();

        HttpSession session = request.getSession();



        try {
            Programme programme = new Programme();
            //验证session是否存在
            User user = null;
            if(session.getAttribute(SESSION_KEY_USER) != null){

            }else {
                user = (User)session.getAttribute(SESSION_KEY_USER);

            }

            programme.setName(requestBody.get("name").toString());
            programme.setState(ProgrammeState.UNPUBLISHED.key);
            String sn = AdsTool.getSn();
            programme.setSn(sn);
            if(user != null) programme.setCreateBy(user.getName());
            programmeRepository.save(programme);
            Programme programmeNew = programmeRepository.findBySn(sn);

            JSONArray pieceProgrammes = (JSONArray) requestBody.get("content");

            for (Object pieceProgramme1 : pieceProgrammes) {
                JSONObject object = (JSONObject) pieceProgramme1;
                PieceProgramme pieceProgramme = new PieceProgramme();
                pieceProgramme.setIndexNum((Integer) object.get("index"));
                pieceProgramme.setTimeSecond((Integer) object.get("time"));
                pieceProgramme.setMaterialId(((Integer) object.get("materialId")).longValue());
                pieceProgramme.setProgrammeId(programmeNew.getId());

                pieceProgrammeRepository.save(pieceProgramme);
            }


        } catch (Exception ex) {
            res.setErrorMsg(ex.getMessage());
            logger.error("/api/programme/add", ex);
        }


        return res;
    }


    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes publish(@RequestBody JSONObject body) {
        BaseRes res = new BaseRes();

        try {
            JSONArray deviceArray = (JSONArray) body.get("devices");
            for (Object aDeviceArray : deviceArray) {
                Publish publish = new Publish();
                publish.setBeginDate(new Date((Long) body.get("beginDate")));
                publish.setEndDate(new Date((Long) body.get("endDate")));
                publish.setProgrammeId(((Integer) body.get("programmeId")).longValue());
                publish.setDeviceId(((Integer) aDeviceArray).longValue());
                publishRepository.save(publish);
            }


        } catch (Exception ex) {
            res.setErrorMsg(ex.getMessage());
            logger.error("/api/programme/publish", ex);
        }

        return res;
    }


    /**
     * 广告机获取节目
     * @param sn
     * @return
     */
    @RequestMapping(value = "getProgramme", method = RequestMethod.POST)
    @ResponseBody
    public DataRes<HashMap<String,Object>> getProgramme(@RequestParam String sn) {
        DataRes<HashMap<String,Object>> res = new DataRes<HashMap<String,Object>>();

        Device device = deviceRepository.findDeviceBySn(sn);

        if (device == null) {
            res.setErrorMsg("设备未注册");
            return res;
        }

        HashMap<String, Object> result = new HashMap<>();

        try {
            Publish publish = publishRepository.findTopByDeviceIdOrderByCreateDateDesc(device.getId());

            if (publish == null) {
                res.setErrorMsg("尚未发布节目");
                return res;
            }

            result.put("programmeId", publish.getProgrammeId());
            result.put("deviceId", publish.getDeviceId());
            List<PieceProgramme> pieceProgrammes = pieceProgrammeRepository.findByProgrammeIdOrderByIndexNumAsc(publish.getProgrammeId());
            List<HashMap<String,Object>> contentList = new ArrayList<HashMap<String,Object>>();
            for (PieceProgramme pieceProgramme:pieceProgrammes) {
                HashMap<String, Object> content = new HashMap<String, Object>();
                content.put("id", pieceProgramme.getId());
                content.put("materialId", pieceProgramme.getMaterialId());
                content.put("indexNum", pieceProgramme.getIndexNum());
                content.put("timeSecond", pieceProgramme.getTimeSecond());
                Material material = materialRepository.findOne(pieceProgramme.getMaterialId());
                if (material != null) {
                    content.put("path", material.getPath());
                    content.put("type", material.getType());
                } else {
                    content.put("path", null);
                    content.put("type", null);
                }
                contentList.add(content);
            }
            result.put("content", contentList);

            res.setData(result);

        } catch (Exception ex) {
            res.setErrorMsg(ex.getMessage());
            logger.error("/api/programme/getProgramme", ex);
        }

        return res;
    }


    /**
     * 节目列表
     * @param username
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public DataRes<List<Programme>> list(@RequestParam String username) {
        DataRes<List<Programme>> res = new DataRes<List<Programme>>();

        try {
            List<Programme> programmes = programmeRepository.findByCreateBy(username);
            res.setData(programmes);
        } catch (Exception ex) {
            res.setErrorMsg(ex.getMessage());
            logger.error("/api/programme/getProgramme", ex);
        }

        return res;
    }





    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@RequestParam Long id) {
        BaseRes res = new BaseRes();

        programmeRepository.delete(id);

        return res;
    }
}
