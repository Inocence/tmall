package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    Goods getOne(int goodsId);
    List<Goods> getList(Goods goods);
    List<Goods> getSearch(SearchGoodsItem igi);
}
