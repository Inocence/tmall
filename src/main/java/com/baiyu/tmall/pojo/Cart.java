package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Cart {
    private int cartId;
    private int goodsId;
    private int userId;
    private int num;
    private Goods goods;
    private String createTime;
}
