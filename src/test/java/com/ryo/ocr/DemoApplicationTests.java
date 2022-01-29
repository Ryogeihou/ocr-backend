package com.ryo.ocr;

import com.ryo.ocr.dao.OrderDao;
import com.ryo.ocr.entity.OrderEntity;
import com.ryo.ocr.service.RecognitionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private OrderDao orderDao;

	@Value("${web.upload-path}")
	private String uploadPath;

	@Autowired
	RecognitionService recService;

	@Test
	void mkdirTest () {


	}
//	@Test
//	void ocrTest1 () {
//		String bath = uploadPath;
//		test1(bath + "test02.jpeg");
//	}
//
//	/**
//	 * 根据路径识别文字结果
//	 * @param path
//	 */
//	void test1(String path) {
//		File file = new File(path);
//		ITesseract it = new Tesseract();
//		try {
//			String result = it.doOCR(file);
//			System.out.println("识别结果:"+result );
//		} catch (TesseractException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	void test0() {
//		String bath = uploadPath;
//		test1(bath + "WechatIMG515.jpeg");
//	}
//
//	@Test
//	public static void test1(String path) {
//		File file = new File(path);
//		ITesseract it = new Tesseract();
//		try {
//			String result = it.doOCR(file);
//			System.out.println("识别结果:"+result );
//		} catch (TesseractException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Test
//	void listTest() {
//	    orderList();
//	}

}
