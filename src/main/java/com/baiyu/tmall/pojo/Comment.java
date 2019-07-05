package com.baiyu.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
