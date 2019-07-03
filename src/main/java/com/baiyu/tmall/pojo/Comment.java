package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Comment {
    private int commentId;
    private int userId;
    private int ordersId;
    private int goodsId;
    private int itemId;
    private String content;
    private String createTime;
}
