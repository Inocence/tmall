package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrdersMapper {
    void insert(Orders orders);
    Orders getOne(int id);
    void update(Orders orders);
    List<Orders> getSearch(Orders orders);
}
