package com.baiyu.tmall.pojo.vo;

import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.pojo.Orders;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class OrdersVo {
    private Orders orders;
    private List<Item> item;
}
