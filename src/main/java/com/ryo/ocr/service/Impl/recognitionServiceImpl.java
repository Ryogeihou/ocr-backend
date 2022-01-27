package com.ryo.ocr.service.Impl;

import com.ryo.ocr.service.RecognitionService;
import com.ryo.ocr.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service("recognitionService")
public class recognitionServiceImpl implements RecognitionService {

    @Value("${web.upload-path}")
    private String uploadPath;

    @Override
    public R receiveImg(MultipartFile file) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String oldName = file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath, oldName));
            String imgLocation = uploadPath + oldName;
            String txtName = imgLocation.substring(0,imgLocation.indexOf("."));
            String result =runCMD(String.format("tesseract %s %s -l jpn", imgLocation,txtName));
            Thread.sleep(8000);
            return R.ok().put("imgLocation", imgLocation).put("result", result);
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
            return R.error(500, e.toString());
        }
    }

    /*
    runCMD & receive return value
     */
    @Override
    public String runCMD(String command) {
        StringBuilder sb =new StringBuilder();
        try {
            Process process=Runtime.getRuntime().exec(command);
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                sb.append(line+"\n");
            }
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }
}
