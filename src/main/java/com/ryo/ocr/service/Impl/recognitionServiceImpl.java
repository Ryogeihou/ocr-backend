package com.ryo.ocr.service.Impl;

import com.ryo.ocr.entity.RecogEntity;
import com.ryo.ocr.service.RecognitionService;
import com.ryo.ocr.utils.R;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
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

    //　正規表現文
    String noNum = "[^0-9]";
    String date = "([1-9]\\d{3})[/|-|年]([0-9]{1,2})[/|-|月]([0-9]{1,2})";
    String amountRow = "(小計|言十|商品代金).*[0-9]*";

    @Override
    public R receiveImg(MultipartFile file) {
        File dir = new File(uploadPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath, oldName));//アップロード場所に保存する
            String imgLocation = uploadPath + oldName;
            String txtName = imgLocation.substring(0,imgLocation.indexOf("."));//txtファイル名の前処理
            // String txtName = "stdout";
            // 今回CMDフォマット：　tesseract imgPath outputFileName options language
            // もし outputFileName = "stdout",　結果はCLIに表示する
            runCMD(String.format("tesseract %s %s -l jpn", imgLocation,txtName));
            // 結果をまとめる
            RecogEntity result = readTxt(txtName);
            return R.ok().put("imgLocation", imgLocation).put("result", result);
        } catch (IOException e) {
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
            // 以下はCMDのアウトプットがある場合、結果をリターンする
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
    @Override
    public RecogEntity readTxt(String fileName) throws IOException {

        String filePath =  fileName + ".txt";// ファイル名処理
        RecogEntity resultEntity = new RecogEntity();
        FileInputStream fin = new FileInputStream(filePath );
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";

        ArrayList<String> content = new ArrayList<>();
        while((strTmp = buffReader.readLine())!=null){
            if (strTmp.length() != 0){
                content.add(strTmp);//　結果をcontentに渡す
                // 時間と小計がある行のインデックスを探す
                if (row2Date(strTmp) != null && resultEntity.getDateIndex() == null) {
                    resultEntity.setDateIndex(content.indexOf(strTmp));
                    resultEntity.setDate(strTmp.substring(0,strTmp.lastIndexOf(" ")));
                }
                if (row2Amount(strTmp) != null && resultEntity.getAmountIndex() == null) {
                    resultEntity.setAmountIndex(content.indexOf(strTmp));
                }
            }
        } // インデックスを渡す
        resultEntity.setDateIndex( resultEntity.getDateIndex() == null ? 0 : resultEntity.getDateIndex());
        resultEntity.setAmountIndex( resultEntity.getAmountIndex() == null ? content.size() : resultEntity.getAmountIndex());
        // 時間と小計を挟んでいる行を商品がある行に判定する
        ArrayList<String> itemList = new ArrayList(content.subList(resultEntity.getDateIndex() + 1 ,resultEntity.getAmountIndex()));
        JSONArray itemJson = new JSONArray();
        for (int i = 0 ;i<itemList.toArray().length;i++){
            itemJson.add(row2Json(itemList.get(i)));
        }

        // 結果の実体に値を渡す
        resultEntity.setContent(content);
        resultEntity.setJsonArray(itemJson);
        return resultEntity;
    };

    public JSONObject row2Json(String row) {
        String item =  row.lastIndexOf(" ")> 0 ? row.substring(0,row.lastIndexOf(" ")).trim(): row;
        String price = row.lastIndexOf(" ")> 0 ? row.substring(row.lastIndexOf(" ")+1) : "";
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
        Pattern rDate = Pattern.compile(date);
        Matcher mDate = rDate.matcher(row);
        boolean rd = mDate.find();
        if (!rd) return null;
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

    public String row2StoreName(String row) {
        String telNum = "([0-9]-[0-9]{4}|[0-9]{2}-[0-9]{3}|[0-9]{3}-[0-9]{2}|[0-9]{4}-[0-9])-[0-9]{4}$";
        Pattern rTel = Pattern.compile(telNum);
        Matcher mTel = rTel.matcher(row);
        boolean rt = mTel.find();
        if (!rt) return null;
        System.out.println(mTel.group(0));
        return mTel.group(0);
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
