package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.GoodsIndex;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    Goods getOne(int id);
    List<Goods> getList(Goods goods);
    List<Goods> getSearch(SearchGoodsItem igi);
    List<GoodsIndex> getIndexList(List<Integer> cateIds);
}
