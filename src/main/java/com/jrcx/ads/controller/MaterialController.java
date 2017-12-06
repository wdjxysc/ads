package com.jrcx.ads.controller;


import com.jrcx.ads.domain.Material;
import com.jrcx.ads.domain.dao.MaterialRepository;
import com.jrcx.ads.res.BaseRes;
import com.jrcx.ads.res.DataRes;
import com.jrcx.ads.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator.
 * @date 2017/11/27
 */
@Controller
@RequestMapping(value = "/api/material")
public class MaterialController extends BaseController {

    private static final String uploadPath = "E:/fileUpload/";

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        BaseRes res = new BaseRes();

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题，linux下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;

//        String filePath = request.getSession().getServletContext().getRealPath("fileUpload/");
        String filePath = uploadPath;
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            Material material = new Material();
            material.setName(fileName);
            material.setPath(filePath + fileName);
            material.setCreateDate(new Date());

            if (contentType.contains("image")) {
                material.setType(1);
            } else {
                material.setType(0);
            }
            materialRepository.save(material);
        } catch (Exception ex) {
            res.setErrorMsg("上传失败：" + ex.getMessage());
        }

        return res;
    }


    //文件下载相关代码
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String downloadFile(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
//        String fileName = "FileUploadTests.java";
        Material material = materialRepository.findOne(id);

        if (material != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
//            String filePath = request.getServletContext().getRealPath("//WEB-INF//");
            String filePath = material.getPath();
            String fileName = material.getName();

            FileUtil.downloadFile(filePath, fileName, response);
        }
        return null;
    }


    //多文件上传
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "upload successful";
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@RequestParam Long id) {
        BaseRes res = new BaseRes();

        materialRepository.delete(id);

        return res;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes update(HttpServletRequest request) {
        BaseRes res = new BaseRes();


        return res;
    }


    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public DataRes<List<Material>> list(@RequestParam Integer page, @RequestParam Integer size) {
        DataRes<List<Material>> res = new DataRes<>();

        try {
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            org.springframework.data.domain.Pageable pageable = new PageRequest(page, size, sort);
            Page<Material> materials = materialRepository.findAll(pageable);

            res.setData(materials.getContent());
        } catch (Exception ex) {
            res.setErrorMsg(ex.getMessage());
        }


        return res;
    }

}
