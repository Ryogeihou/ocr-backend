package com.ryo.ocr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RequestMapping("/upload")
@RestController
public class uploadController {

    @Value("${web.upload-path}")
    private String uploadPath;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @RequestMapping("/img")
//    public String uploadImg(@RequestParam("file")MultipartFile file){
    public String uploadImg(@RequestParam("file")MultipartFile file){

//        String format = sdf.format(new Date());
//        File folder = new File( uploadPath + format);
//        if (!folder.isDirectory()) {
//            folder.mkdirs();
//        }
        String oldName = file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath, oldName));

            String imgLocation = uploadPath + oldName;
            return imgLocation;
        } catch (IOException e) {
            System.out.println(e);
        }

        return "ok";
    }
}

//        String newName = UUID.randomUUID().toString()
//                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
// 文件保存
//            file.transferTo(new File(folder, newName).toPath().toAbsolutePath());
// 返回上传文件的访问路径
//            String filePath = request.getScheme() + "://" + request.getServerName()
//                    + ":" + request.getServerPort() + format + newName;
//            throw new CustomException(CustomExceptionType.SYSTEM_ERROR);
