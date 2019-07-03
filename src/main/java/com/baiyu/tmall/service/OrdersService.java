package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Orders;
import com.baiyu.tmall.pojo.item.SearchCartItem;
import com.baiyu.tmall.pojo.item.SearchOrdersItem;

import java.util.List;


public interface OrdersService {
    Orders getOne(int id);
    int orders(SearchCartItem sci);
    void update(Orders orders);
    List<SearchOrdersItem> getList(Orders orders);
    int count(Orders orders);
}
