package com.ryo.ocr.controller;

import com.ryo.ocr.entity.RecogEntity;
import com.ryo.ocr.utils.PageUtils;
import com.ryo.ocr.utils.R;
import com.ryo.ocr.entity.OrderEntity;
import com.ryo.ocr.service.OrderService;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
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

    @RequestMapping("/save")
    public R save(@RequestBody OrderEntity orderEntity){

        orderEntity.setItemList(orderEntity.getJsonArray().toString());
        orderService.save(orderEntity);

        return R.ok();
    }
    @RequestMapping("/saveBatch")
    public R saveBatch(@RequestBody Collection<OrderEntity> orderList){
        orderService.saveBatch(orderList);

        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/delete/{orderId}")
    public R deleteOne(@PathVariable("orderid") Integer orderId){
        orderService.removeById(orderId);

        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody OrderEntity orderEntity) {
        orderEntity.setItemList(orderEntity.getJsonArray().toString());
        orderService.updateById(orderEntity);

        return R.ok();
    }

    @RequestMapping("/create")
    public R create(@RequestBody OrderEntity orderEntity) {
        orderService.save(orderEntity);

        return R.ok();
    }
}
