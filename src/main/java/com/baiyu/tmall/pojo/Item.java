package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Item {
    private int itemId;
    private int ordersId;
    private int goodsId;
    private int userId;
    private int num;
    private int price;
    private int status;
    private String createTime;
    private Goods goods;
    private Orders orders;
}
