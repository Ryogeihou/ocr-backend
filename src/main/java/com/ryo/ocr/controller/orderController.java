package com.ryo.ocr.controller;

import com.ryo.ocr.utils.PageUtils;
import com.ryo.ocr.utils.R;
import com.ryo.ocr.entity.OrderEntity;
import com.ryo.ocr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("order")
public class orderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/info/{orderid}")
    public OrderEntity info(@PathVariable("orderid") Integer orderid){
        OrderEntity order = orderService.getById(orderid);

        return order;
    }

    @RequestMapping("/list")
    public R orderList(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }
}
