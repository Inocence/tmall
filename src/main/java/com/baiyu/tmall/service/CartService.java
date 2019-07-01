package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.item.SearchCartItem;

import java.util.List;

public interface CartService {
    List<Cart> getAll();
    void insert(Cart cart);
    void delete(Cart cart);
    void update(Cart cart);
    List<Cart> getSearch(SearchCartItem sci);
}
