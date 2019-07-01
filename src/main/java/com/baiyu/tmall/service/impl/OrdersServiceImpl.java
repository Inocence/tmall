package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.ItemMapper;
import com.baiyu.tmall.mapper.OrdersMapper;
import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.pojo.Orders;
import com.baiyu.tmall.pojo.item.SearchCartItem;
import com.baiyu.tmall.pojo.item.SearchItemItem;
import com.baiyu.tmall.service.CartService;
import com.baiyu.tmall.service.ItemService;
import com.baiyu.tmall.service.OrdersService;
import com.baiyu.tmall.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired(required = false)
    private OrdersMapper ordersMapper;

    @Autowired(required = false)
    private ItemService itemService;

    @Autowired(required = false)
    private CartService cartService;

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

}
