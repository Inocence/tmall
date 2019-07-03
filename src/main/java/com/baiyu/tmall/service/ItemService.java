package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Item;


public interface ItemService {
    void insert(Item item);
    void update(Item item);
    Item getOne(int id);
}
