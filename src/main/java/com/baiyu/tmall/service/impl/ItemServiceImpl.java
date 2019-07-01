package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.GoodsMapper;
import com.baiyu.tmall.mapper.ItemMapper;
import com.baiyu.tmall.mapper.OrdersMapper;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.pojo.Orders;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;
import com.baiyu.tmall.pojo.item.SearchItemItem;
import com.baiyu.tmall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Autowired(required = false)
    private OrdersMapper ordersMapper;

    @Override
    public void insert(Item item) {
        itemMapper.insert(item);
    }

    @Override
    public List<Item> getSearch(Orders orders) {
        //获取订单列表
        List<Orders> ordersList = ordersMapper.getSearch(orders);
        List<Integer> ordersIds = ordersList.stream().map(Orders::getOrdersId).collect(Collectors.toList());
        Map<Integer, List<Orders>> ordersMap = ordersList.stream().collect(Collectors.groupingBy(Orders::getOrdersId));

        //获取item列表
        SearchItemItem sii = new SearchItemItem();
        sii.setOrdersIds(ordersIds);
        List<Item> items = itemMapper.getSearch(sii);

        //获取商品列表
        List<Integer> goodsIds = items.stream().map(Item::getGoodsId).collect(Collectors.toList());
        SearchGoodsItem sgi = new SearchGoodsItem();
        sgi.setGoodsIds(goodsIds);
        List<Goods> goodsList = goodsMapper.getSearch(sgi);

        //组装数据
        Map<Integer, List<Goods>> goodsMap = goodsList.stream().collect(Collectors.groupingBy(Goods::getGoodsId));
        for (Item item : items) {
            if(goodsMap.containsKey(item.getGoodsId())) {
                item.setGoods(goodsMap.get(item.getGoodsId()).get(0));
            }
            if(ordersMap.containsKey(item.getOrdersId())) {
                item.setOrders(ordersMap.get(item.getOrdersId()).get(0));
            }
        }

        return items;
    }
}
