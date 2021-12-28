package com.ryo.ocr.controller;

import com.ryo.ocr.entity.OrderEntity;
import com.ryo.ocr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item/")
public class itemController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("info/{itemid}")
    public OrderEntity info(@PathVariable("orderid") Integer orderid){
        OrderEntity order = orderService.getById(orderid);

        return order;
    }
}
