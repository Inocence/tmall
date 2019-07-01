package com.baiyu.tmall.pojo.item;

import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.Orders;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchCartItem {
    private List<Integer> cartIds;
    private List<Cart> carts;
    private Orders orders;
}
