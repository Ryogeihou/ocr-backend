package com.ryo.ocr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryo.ocr.utils.PageUtils;
import com.ryo.ocr.entity.OrderEntity;

import java.util.Map;

public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
