package com.baiyu.tmall.pojo;


import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class Goods {
    private int goodsId;
    private int storeId;
    private int cateId;
    private String name;
    private String content;
    private Integer price;
    private String cover;
    private String img;
    private List<String> imgList;
    private String createTime;
    private String updateTime;

    private Cate cate;
}
