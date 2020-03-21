package com.zhang.comunity.controller;

import com.zhang.comunity.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/3/17 17:26
 */
@Slf4j
@Controller
public class UploadController {
    @Value("${file.uploadFilePath}")
    private String uploadFilePath;

    @Value("${myServer.address}")
    private String serverAddress;


    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public FileDTO upload(@RequestParam(value="editormd-image-file",required=false)MultipartFile file, HttpServletRequest request){
        FileDTO fileDTO=new FileDTO();
        if (file==null||file.isEmpty()){
            return fileDTO.errorOf();
        }
        //获取文件名加后缀
        String fileOriginalFilename = file.getOriginalFilename();

        // 项目在容器中实际发布运行的根路径
        File rootPath=new File(uploadFilePath);
        if (!rootPath.exists() && !rootPath.isDirectory()){
            rootPath.mkdir();
        }
        String realPath=rootPath.getAbsolutePath();
        log.info("保存文件的路径为："+realPath);
        // 自定义的文件名称
        String trueFileName = String.valueOf(System.currentTimeMillis()) + fileOriginalFilename;

        File uploadFile=new File(realPath,trueFileName);
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return fileDTO.errorOf();
        }
        log.info("上传文件的访问地址:"+serverAddress+"/"+trueFileName);


        return fileDTO.okOf("/"+trueFileName);

    }


}
