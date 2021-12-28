package com.ryo.ocr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("order_entity")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type=IdType.AUTO)
    private Integer id;

    private Long memberId;

    private LocalDateTime createTime;

    private String memberUsername;

    private Integer totalAmount;

    private Integer payAmount;

    private String note;

    private Integer deleteStatus;

    private LocalDateTime modifyTime;
}
