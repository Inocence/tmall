package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.pojo.vo.ItemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ItemMapper {
    Item getOne(int id);
    void insert(Item item);
    void update(Item item);
    List<Item> getSearch(ItemVo sii);
    int count(Item item);
}
