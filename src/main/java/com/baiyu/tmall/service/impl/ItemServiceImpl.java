package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.GoodsMapper;
import com.baiyu.tmall.mapper.ItemMapper;
import com.baiyu.tmall.mapper.OrdersMapper;
import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Override
    public void insert(Item item) {
        itemMapper.insert(item);
    }

    @Override
    public void update(Item item) {
        itemMapper.update(item);
    }

    @Override
    public Item getOne(int id) {
        return itemMapper.getOne(id);
    }
}
