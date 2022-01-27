//package com.ryo.ocr;
//
//import com.ryo.ocr.dao.OrderDao;
//import com.ryo.ocr.entity.OrderEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.*;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@SpringBootTest
//class DemoApplicationTests {
//
//	@Autowired
//	private OrderDao orderDao;
//
//	@Value("${web.upload-path}")
//	private String uploadPath;
//
//
//	@Test
//	void readTxt() throws IOException {
//	    String fileName = "1c.txt";
//	    String filePath = uploadPath + fileName;
//	   	String rDate = "([0-9]|[a-z]|[A-Z]){4}(/|-|年)([0-9]|[a-z]|[A-Z]){1,2}(/|-|月)([0-9]|[a-z]|[A-Z]){1,2}";
//		Pattern pattern = Pattern.compile(rDate);
//		String testTxt = "2021年11月27日(土)";
//
//		FileInputStream fin = new FileInputStream(filePath);
//		InputStreamReader reader = new InputStreamReader(fin);
//		BufferedReader buffReader = new BufferedReader(reader);
//		String strTmp = "";
////		Arrays arr = ;
//		Matcher test = pattern.matcher(testTxt);
//		System.out.println("test");
//		boolean rs = test.find();
//		if (rs) {
//			System.out.println(test.group(2));
//		}
////		System.out.println(test.find());
////		while((strTmp = buffReader.readLine())!=null){
////			Matcher matcher = pattern.matcher(strTmp);
////			boolean rs = matcher.matches();
////			System.out.println("rs");
////			System.out.println(rs);
////			if (rs) {
////				System.out.println("rs:");
////				System.out.println(rs);
////			}
//////			System.out.println(strTmp);
////		}
//		buffReader.close();
//
//	}
//
//	@Test
//	void contextLoads() {
////		List<OrderEntity> list = orderDao.selectList(null);
////		list.forEach(System.out::println);
//		OrderEntity orderEntity = new OrderEntity();
//		orderEntity.setMemberId(11L);
//		orderEntity.setPayAmount(10001);
//		orderEntity.setCreateTime(LocalDateTime.now());
//		int rows = orderDao.insert(orderEntity);
//		System.out.println(rows);
//	}
//	@Test
//	void info() {
//		List<OrderEntity> list = orderDao.selectList(null);
//		list.forEach(System.out::println);
//	}
//
//
//	@Test
//	void cmdTest() {
//		String back=runCMD("tesseract /Users/ryo/data/test02.jpeg stdout -l jpn");
//		System.out.println(back);
//	}
//	String runCMD(String command) {
//		StringBuilder sb =new StringBuilder();
//		try {
//			Process process=Runtime.getRuntime().exec(command);
//			InputStream inputStream = process.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//			String line;
//			while((line=bufferedReader.readLine())!=null)
//			{
//				sb.append(line+"\n");
//			}
//		} catch (Exception e) {
//			return e.toString();
//		}
//		return sb.toString();
//	}
////	@Test
////	void ocrTest1 () {
////		String bath = uploadPath;
////		test1(bath + "test02.jpeg");
////	}
////
////	/**
////	 * 根据路径识别文字结果
////	 * @param path
////	 */
////	void test1(String path) {
////		File file = new File(path);
////		ITesseract it = new Tesseract();
////		try {
////			String result = it.doOCR(file);
////			System.out.println("识别结果:"+result );
////		} catch (TesseractException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
////
////	@Test
////	void test0() {
////		String bath = uploadPath;
////		test1(bath + "WechatIMG515.jpeg");
////	}
////
////	@Test
////	public static void test1(String path) {
////		File file = new File(path);
////		ITesseract it = new Tesseract();
////		try {
////			String result = it.doOCR(file);
////			System.out.println("识别结果:"+result );
////		} catch (TesseractException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
////	@Test
////	void listTest() {
////	    orderList();
////	}
//
//}
