package com.ryo.ocr.service;

import com.ryo.ocr.utils.R;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


public interface RecognitionService {

    R receiveImg(MultipartFile file);
    String runCMD(String command);
    String row2Date (String row);
    String row2StoreName (String row);
    Integer row2totalAmount (String row);
}
