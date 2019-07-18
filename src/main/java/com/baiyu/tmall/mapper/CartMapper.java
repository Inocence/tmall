package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> getAll();
    void insert(Cart cart);
    void delete(Cart cart);
    void update(Cart cart);
    List<Cart> getSearch(CartVo sci);
}
