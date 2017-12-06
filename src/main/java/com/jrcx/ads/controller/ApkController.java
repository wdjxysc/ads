package com.jrcx.ads.controller;

import com.jrcx.ads.controller.BaseController;
import com.jrcx.ads.domain.Apk;
import com.jrcx.ads.domain.dao.ApkRepository;
import com.jrcx.ads.res.BaseRes;
import com.jrcx.ads.res.DataRes;
import com.jrcx.ads.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author Administrator.
 * @date 2017/11/28
 */
@Controller
@RequestMapping(value = "/api/apk")
public class ApkController extends BaseController {

    final String apkPath = "E:/fileUpload/apk/";

    final
    ApkRepository apkRepository;

    @Autowired
    public ApkController(ApkRepository apkRepository) {
        this.apkRepository = apkRepository;
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes upload(@RequestParam Integer versionNum, @RequestParam("file") MultipartFile file){
        logger.info("uploadApk");
        BaseRes res = new BaseRes();

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题，linux下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;

//        String filePath = request.getSession().getServletContext().getRealPath("fileUpload/");
        String filePath = apkPath;
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            Apk apk = new Apk();
            apk.setName(fileName);
            apk.setVersionNum(versionNum);
            apk.setPath(filePath + fileName);
            apkRepository.save(apk);

        } catch (Exception ex) {
            logger.error("uploadApk", ex);
            res.setErrorMsg("上传失败：" + ex.getMessage());
        }

        return res;
    }


    //文件下载相关代码
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String download(@RequestParam Long id, HttpServletResponse response) {
        logger.info("downloadApk");
//        String fileName = "FileUploadTests.java";
        Apk apk = apkRepository.findOne(id);

        if (apk != null) {
            String filePath = apk.getPath();
            String fileName = apk.getName();
            FileUtil.downloadFile(filePath, fileName, response);
        }
        return null;
    }

    //文件下载相关代码
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
        logger.info("deleteApk");
        BaseRes res = new BaseRes();

        try{
            apkRepository.delete(id);
        }catch (Exception ex){
            logger.error("deleteApk", ex);
            res.setErrorMsg(ex.getMessage());
        }

        return res;
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    @ResponseBody
    public DataRes<Apk> check(){
        logger.info("checkApk");
        DataRes<Apk> res = new DataRes<>();

        try{
            Apk apk = apkRepository.findTopByOrderByVersionNumDesc();

            res.setData(apk);
        }catch (Exception ex){
            logger.error("checkApk", ex);
            res.setErrorMsg(ex.getMessage());
        }

        return res;
    }
}
