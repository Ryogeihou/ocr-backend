package com.ryo.ocr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("order_item")
public class ItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;

    private Integer orderId;

    private String spuName;

    private Integer categoryId;

    private Integer skuId;

    private String skuName;

    private Integer skuPrice;

    private Integer skuQuantity;
}
