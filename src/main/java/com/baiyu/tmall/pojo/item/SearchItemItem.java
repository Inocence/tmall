package com.baiyu.tmall.pojo.item;

import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.Orders;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchItemItem {
    private List<Integer> ordersIds;
}
