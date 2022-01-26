//package com.ryo.ocr;
//
//import com.ryo.ocr.dao.OrderDao;
//import com.ryo.ocr.entity.OrderEntity;
//import com.ryo.ocr.service.RecognitionService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.net.ssl.HttpsURLConnection;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class DemoApplicationTests {
//
//	@Autowired
//	private OrderDao orderDao;
//
//	@Value("${web.upload-path}")
//	private String uploadPath;
//
//	@Autowired
//	RecognitionService recService;
//
//	@Test
//	void getDate() {
//		String fileName = "0011.txt";
////		String date =
//	}
//
//	@Test
//	void process () {
//		String row = "森菓了チョ]モナカツ ャッボ          ※14";
//		String item = row.substring(0,row.lastIndexOf(" "));
//		String price = row.substring(row.lastIndexOf(" ")+1);
//		System.out.println(item.trim() + "_" + price);
//	}
//
//	@Test
//	void readTxt() throws IOException {
//
////	    String fileName = "1c.txt";
//		String fileName = "0011.txt";
//		String filePath = uploadPath + fileName;
//
//		FileInputStream fin = new FileInputStream(filePath);
//		InputStreamReader reader = new InputStreamReader(fin);
//		BufferedReader buffReader = new BufferedReader(reader);
//		String strTmp = "";
////		String content = "";
////		List<String> content = null;
////		ArrayList<String> content = new ArrayList();
//		String testTxt = "2021年11月27日(土) 23時";
//		String testTel = "量話 : 049-234-5775 的\n";
//		ArrayList<String> content = new ArrayList<>();
//		while((strTmp = buffReader.readLine())!=null){
//			if (strTmp.length() != 0){
//				content.add(strTmp.replaceAll(",|。| ", ""));
//			}
//		}
//		System.out.println(content);
////		System.out.println(content);
////		String testTe = recService.row2StoreName(content);
////		LocalDate testDate = recService.row2Date(content);
////		System.out.println(testTe);
////		System.out.println(testDate);
////		content.forEach(row -> System.out.println(row));
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
//	public String runCMD(String command) {
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
//	@Test
//	public  void testUrl() throws Exception {
//
//		HttpURLConnectionExample http = new HttpURLConnectionExample();
//
//		System.out.println("Testing 1 - Send Http GET request");
//		http.sendGet();
//
////		System.out.println("\nTesting 2 - Send Http POST request");
////		http.sendPost();
//
//	}
//	public class HttpURLConnectionExample {
//
//		private final String USER_AGENT = "Mozilla/5.0";
//
//
//		// HTTP GET请求
//		private void sendGet() throws Exception {
//
//			String url = "http://www.google.com/search?q=03-3560-3895";
//
//			URL obj = new URL(url);
//			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//			//默认值我GET
//			con.setRequestMethod("GET");
//
//			//添加请求头
//			con.setRequestProperty("User-Agent", USER_AGENT);
//
//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' request to URL : " + url);
//			System.out.println("Response Code : " + responseCode);
//
//			BufferedReader in = new BufferedReader(
//					new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			//打印结果
//			System.out.println(response.toString());
//
//		}
//
//		// HTTP POST请求
//		private void sendPost() throws Exception {
//
//			String url = "https://selfsolve.apple.com/wcResults.do";
//			URL obj = new URL(url);
//			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//			//添加请求头
//			con.setRequestMethod("POST");
//			con.setRequestProperty("User-Agent", USER_AGENT);
//			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//			String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
//
//			//发送Post请求
//			con.setDoOutput(true);
//			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//			wr.writeBytes(urlParameters);
//			wr.flush();
//			wr.close();
//
//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'POST' request to URL : " + url);
//			System.out.println("Post parameters : " + urlParameters);
//			System.out.println("Response Code : " + responseCode);
//
//			BufferedReader in = new BufferedReader(
//					new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			//打印结果
//			System.out.println(response.toString());
//
//		}
//
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
