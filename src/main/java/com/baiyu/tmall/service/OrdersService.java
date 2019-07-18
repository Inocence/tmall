package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Orders;
import com.baiyu.tmall.pojo.vo.CartVo;
import com.baiyu.tmall.pojo.vo.OrdersVo;

import java.util.List;


public interface OrdersService {
    Orders getOne(int id);
    int orders(CartVo sci);
    void update(Orders orders);
    List<OrdersVo> getList(Orders orders);
    int count(Orders orders);
}
