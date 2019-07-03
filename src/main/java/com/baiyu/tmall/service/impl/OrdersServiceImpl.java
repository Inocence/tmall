package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.GoodsMapper;
import com.baiyu.tmall.mapper.ItemMapper;
import com.baiyu.tmall.mapper.OrdersMapper;
import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.pojo.Orders;
import com.baiyu.tmall.pojo.item.SearchCartItem;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;
import com.baiyu.tmall.pojo.item.SearchItemItem;
import com.baiyu.tmall.pojo.item.SearchOrdersItem;
import com.baiyu.tmall.service.CartService;
import com.baiyu.tmall.service.ItemService;
import com.baiyu.tmall.service.OrdersService;
import com.baiyu.tmall.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired(required = false)
    private OrdersMapper ordersMapper;

    @Autowired(required = false)
    private ItemService itemService;

    @Autowired(required = false)
    private CartService cartService;

    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Override
    public Orders getOne(int id) {
        return ordersMapper.getOne(id);
    }

    @Override
    public void update(Orders orders) {
        ordersMapper.update(orders);
    }

    @Override
    public int orders(SearchCartItem sci) {
        List<Integer> cartIds = sci.getCarts().stream().map(Cart::getCartId).collect(Collectors.toList());
        sci.setCartIds(cartIds);
        List<Cart> carts = cartService.getSearch(sci);
        int price = 0;
        for (Cart cart : carts) {
            price += cart.getNum() * cart.getGoods().getPrice();
        }

        Orders orders = sci.getOrders();
        orders.setUserId(1);
        orders.setNumber(String.valueOf(System.currentTimeMillis()));
        orders.setPrice(price);
        orders.setStatusTime(Tool.timeFormat());
        orders.setCreateTime(Tool.timeFormat());
        orders.setComment("");
        orders.setStatus(1);
        orders.setStatusTime(Tool.timeFormat());
        ordersMapper.insert(orders);
        int ordersId = orders.getOrdersId();

        Item item = new Item();
        for (Cart cart : carts) {
            item.setGoodsId(cart.getGoodsId());
            item.setUserId(1);
            item.setNum(cart.getNum());
            item.setPrice(cart.getGoods().getPrice());
            item.setCreateTime(Tool.timeFormat());
            item.setOrdersId(ordersId);
            itemService.insert(item);

            cartService.delete(cart);
        }

        return ordersId;
    }

    @Override
    public List<SearchOrdersItem> getList(Orders orders) {
        List<SearchOrdersItem> soiList = new ArrayList<>();
        //获取订单列表
        List<Orders> ordersList = ordersMapper.getSearch(orders);
        if(ordersList.size() == 0) {
            return soiList;
        }
        List<Integer> ordersIds = ordersList.stream().map(Orders::getOrdersId).collect(Collectors.toList());
        Map<Integer, List<Orders>> ordersMap = ordersList.stream().collect(Collectors.groupingBy(Orders::getOrdersId));

        //获取item列表
        SearchItemItem sii = new SearchItemItem();
        sii.setOrdersIds(ordersIds);
        List<Item> items = itemMapper.getSearch(sii);
        Map<Integer, List<Item>> itemMap = items.stream().collect(Collectors.groupingBy(Item::getOrdersId));

        //获取商品列表
        List<Integer> goodsIds = items.stream().map(Item::getGoodsId).collect(Collectors.toList());
        SearchGoodsItem sgi = new SearchGoodsItem();
        sgi.setGoodsIds(goodsIds);
        List<Goods> goodsList = goodsMapper.getSearch(sgi);

        //组装商品数据
        Map<Integer, List<Goods>> goodsMap = goodsList.stream().collect(Collectors.groupingBy(Goods::getGoodsId));
        for (Item item : items) {
            if(goodsMap.containsKey(item.getGoodsId())) {
                item.setGoods(goodsMap.get(item.getGoodsId()).get(0));
            }
        }

        //组装item数据
        for (Orders orders1 : ordersList) {
            SearchOrdersItem soi = new SearchOrdersItem();
            soi.setOrders(orders1);
            if(itemMap.containsKey(orders1.getOrdersId())) {
                soi.setItem(itemMap.get(orders1.getOrdersId()));
            }
            soiList.add(soi);
        }

        return soiList;
    }

    @Override
    public int count(Orders orders) {
        return ordersMapper.count(orders);
    }

}
