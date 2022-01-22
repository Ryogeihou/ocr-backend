package com.ryo.ocr.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecogEntity {
    private ArrayList<String> content;
    private ArrayList<String> itemRows;
    private Integer dateIndex;
    private Integer amountIndex;
    private String telNum;
    private String date;
}
