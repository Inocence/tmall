package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GoodsIndex {
    private Cate cate;
    private List<Goods> goods;
}
