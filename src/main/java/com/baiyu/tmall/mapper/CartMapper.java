package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.item.SearchCartItem;
import com.baiyu.tmall.pojo.item.SearchCatesItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> getAll();
    void insert(Cart cart);
    void delete(Cart cart);
    void update(Cart cart);
    List<Cart> getSearch(SearchCartItem sci);
}
