package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.pojo.Orders;
import com.baiyu.tmall.pojo.item.SearchItemItem;

import java.util.List;


public interface ItemService {
    void insert(Item item);
    List<Item> getSearch(Orders orders);
}
