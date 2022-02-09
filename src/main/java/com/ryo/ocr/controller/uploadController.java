package com.ryo.ocr.controller;

import com.ryo.ocr.entity.RecogEntity;
import com.ryo.ocr.service.RecognitionService;
import com.ryo.ocr.utils.PageUtils;
import com.ryo.ocr.utils.R;
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

@RequestMapping("/upload")
@RestController
public class uploadController {
    //依頼注入 インタフェースを注入 インスタンスのnew操作をspringに任せる
    @Autowired
    RecognitionService recognitionService;
    //依頼注入 値をパラメタに注入
    @Value("${web.upload-path}")// application.ymlのところで設定した-->/tmp/ocrData
    private String uploadPath;

    @RequestMapping("/img")
    public R uploadImg(@RequestBody MultipartFile file) {
        // Rはレスポンスの容器として使う /src/main/java/com/ryo/ocr/utils/R.java
        R result = recognitionService.receiveImg(file);
        return result;
    }


    @RequestMapping("/query")
    public R queryResult(@RequestParam String fileName) throws IOException {
        RecogEntity recogEntity = recognitionService.readTxt(uploadPath + fileName);
        return R.ok().put("result", recogEntity);
    }
}

//    @RequestMapping("/test")
//    public String ocrtest () {
//        String bath = uploadPath;
//        test1(bath + "test02.jpeg");
//
//        return "ok";
//    }
//    String test1(String path) {
//        File file = new File(path);
//        ITesseract it = new Tesseract();
//        try {
//            String result = it.doOCR(file);
//            System.out.println("识别结果:"+result );
//            return result;
//        } catch (TesseractException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return "xxxxx";
//        }
//    }

//        String newName = UUID.randomUUID().toString()
//                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
// 文件保存
//            file.transferTo(new File(folder, newName).toPath().toAbsolutePath());
// 返回上传文件的访问路径
//            String filePath = request.getScheme() + "://" + request.getServerName()
//                    + ":" + request.getServerPort() + format + newName;
//            throw new CustomException(CustomExceptionType.SYSTEM_ERROR);
