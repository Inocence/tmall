package com.baiyu.tmall.pojo.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GoodsVo {
    private List<Integer> cateIds;
    private List<Integer> goodsIds;
    private String name;
    private int cateId;
    private int total;
}
