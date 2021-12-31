package com.ryo.ocr.service;

import com.ryo.ocr.utils.R;
import org.springframework.web.multipart.MultipartFile;


public interface RecognitionService {

    R receiveImg(MultipartFile file);
    String runCMD(String command);
}
