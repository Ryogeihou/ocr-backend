package com.ryo.ocr.service.Impl;

import com.mysql.cj.xdevapi.JsonArray;
import com.ryo.ocr.entity.RecogEntity;
import com.ryo.ocr.service.RecognitionService;
import com.ryo.ocr.utils.R;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("recognitionService")
public class recognitionServiceImpl implements RecognitionService {

    @Value("${web.upload-path}")
    private String uploadPath;

    String noNum = "[^0-9]";
    String date = "([1-9]\\d{3})[/|-|年]([0-9]{1,2})[/|-|月]([0-9]{1,2})";
    String amountRow = "(小計|言十|商品代金).*[0-9]*";

    @Override
    public R receiveImg(MultipartFile file) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String oldName = file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath, oldName));
            String imgLocation = uploadPath + oldName;
            String txtName = imgLocation.substring(0,imgLocation.indexOf("."));
//            String txtName = "stdout";
            runCMD(String.format("tesseract %s %s -l jpn", imgLocation,txtName));
            Thread.sleep(5000);
            RecogEntity result = readTxt(txtName);
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

    public RecogEntity readTxt(String fileName) throws IOException {

        String filePath =  fileName + ".txt";
        RecogEntity resultEntity = new RecogEntity();
        FileInputStream fin = new FileInputStream(filePath );
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";

        ArrayList<String> content = new ArrayList<>();
        while((strTmp = buffReader.readLine())!=null){
            if (strTmp.length() != 0){
                content.add(strTmp);
                if (row2Date(strTmp) != null) {
                    resultEntity.setDateIndex(content.indexOf(strTmp));
                    resultEntity.setDate(strTmp.substring(0,strTmp.lastIndexOf(" ")));
                }
                if (row2Amount(strTmp) != null && resultEntity.getAmountIndex() == null) {
                    resultEntity.setAmountIndex(content.indexOf(strTmp));
                }
            }
        }
        ArrayList<String> itemList = new ArrayList(content.subList(resultEntity.getDateIndex() + 1 ,resultEntity.getAmountIndex()));
        JSONArray itemJson = new JSONArray();

        for (int i = 0 ;i<itemList.toArray().length;i++){
            itemJson.add(row2Json(itemList.get(i)));
        }

        resultEntity.setContent(content);
        resultEntity.setJsonArray(itemJson);
        return resultEntity;
    };

    public JSONObject row2Json(String row) {
        String item = row.substring(0,row.lastIndexOf(" ")).trim();
        String price = row.substring(row.lastIndexOf(" ")+1);
        Pattern p = Pattern.compile(noNum);
        Matcher m = p.matcher(price);
        price = m.replaceAll("").trim();
        JSONObject json = new JSONObject();
        json.put(item, price);
        return json;
    }

    @Override
    //Todo DateTimeParseException
    public String row2Date(String row) throws DateTimeParseException {
//        String time = "([0-9]{1,2})[時|:]([0-9]{1,2})[分|:]?([0-9]{1,2})";
        Pattern rDate = Pattern.compile(date);
//        Pattern rTime= Pattern.compile(time);
        Matcher mDate = rDate.matcher(row);
//        Matcher mTime= rTime.matcher(row);
        boolean rd = mDate.find();
        if (!rd) return null;
//        boolean rt = mTime.find();
//        String hms;
        String ymd = mDate.group(0).replaceAll("(/|年|月)", "-");
//        if (ymd[6] =="-") {
//
//        }
//        if (rt) {
//            hms = mTime.group(0).replaceAll("(/|時|分)", ":");
//            if (mTime.group(3) == null){
//                hms = hms + ":00";
//            }
//        } else {
//            hms = "08:08:08";
//        }
//        System.out.println(ymd);
//        LocalDateTime dt = LocalDateTime.of(LocalDate.parse(ymd), LocalTime.parse(hms));
//        LocalDate.parse("2016-09-29");
        return ymd;
    }

    public String row2Amount(String row) {
        Pattern rAmount = Pattern.compile(amountRow);
//        Pattern rTime= Pattern.compile(time);
        Matcher mAmount = rAmount.matcher(row.replaceAll(" ",""));
//        Matcher mTime= rTime.matcher(row);
        boolean ra = mAmount.find();
        if (!ra) return null;
        String amount = mAmount.group(0);
        return amount;
    }

    @Override
    public String row2StoreName(String row) {
        String telNum = "([0-9]-[0-9]{4}|[0-9]{2}-[0-9]{3}|[0-9]{3}-[0-9]{2}|[0-9]{4}-[0-9])-[0-9]{4}$";
        Pattern rTel = Pattern.compile(telNum);
        Matcher mTel = rTel.matcher(row);
        boolean rt = mTel.find();
        if (!rt) return null;
        System.out.println(mTel.group(0));
        return mTel.group(0);
    }

    @Override
    public Integer row2totalAmount(String row) {
        return null;
    }

//    public String processItem(String row) {
//        String item = row.substring(0,row.lastIndexOf(" "));
//        String price = row.substring(row.lastIndexOf(" ")+1);
//        Pattern p = Pattern.compile(noNum);
//        Matcher m = p.matcher(price);
//        price = m.replaceAll("").trim();
////        price.replaceAll("[^0-9]","");
//
//        return item.trim() + "    ￥" + price;
//    }
}
