package com.ryo.ocr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ryo.ocr.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemDao extends BaseMapper<OrderEntity>{
}
