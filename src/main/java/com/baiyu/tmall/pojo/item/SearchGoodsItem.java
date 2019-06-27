package com.baiyu.tmall.pojo.item;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchGoodsItem {
    private List<Integer> cateIds;
    private List<Integer> goodsIds;
    private String name;
    private int cateId;
    private int total;
}
