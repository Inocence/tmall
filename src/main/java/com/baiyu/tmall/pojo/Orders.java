package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Orders {
    private int ordersId;
    private int userId;
    private String number;
    private String address;
    private String name;
    private String mobile;
    private String comment;
    private int price;
    private int status;
    private String payTime;
    private String statusTime;
    private String createTime;
}
