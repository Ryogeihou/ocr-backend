package com.ryo.ocr.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryo.ocr.utils.PageUtils;
import com.ryo.ocr.dao.OrderDao;
import com.ryo.ocr.entity.OrderEntity;
import com.ryo.ocr.service.OrderService;
import com.ryo.ocr.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("OrderService")
public class orderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );

        return new PageUtils(page);
    }
}