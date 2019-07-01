package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.CartMapper;
import com.baiyu.tmall.mapper.CateMapper;
import com.baiyu.tmall.mapper.GoodsMapper;
import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.item.SearchCartItem;
import com.baiyu.tmall.pojo.item.SearchCatesItem;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;
import com.baiyu.tmall.service.CartService;
import com.baiyu.tmall.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired(required = false)
    private CartMapper cartMapper;

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Override
    public List<Cart> getAll(){
        List<Cart> carts = cartMapper.getAll();
        return conCarts(carts);
    }

    private List<Cart> conCarts(List<Cart> carts) {
        //查询商品
        List<Integer> goodsIds = carts.stream().map(Cart::getGoodsId).collect(Collectors.toList());
        SearchGoodsItem sgi = new SearchGoodsItem();
        sgi.setGoodsIds(goodsIds);
        List<Goods> goods = goodsMapper.getSearch(sgi);
        Map<Integer, List<Goods>> goodsMap = goods.stream().collect(Collectors.groupingBy(Goods::getGoodsId));
        //组装数据
        for (Cart cart : carts) {
            if(goodsMap.containsKey(cart.getGoodsId())) {
                cart.setGoods(goodsMap.get(cart.getGoodsId()).get(0));
            }
        }

        return carts;
    }

    @Override
    public void insert(Cart cart) {
        cartMapper.insert(cart);
    }

    @Override
    public void delete(Cart cart){
        cartMapper.delete(cart);
    }

    @Override
    public void update(Cart cart){
        cartMapper.update(cart);
    }

    @Override
    public List<Cart> getSearch(SearchCartItem sci){
        List<Cart> carts = cartMapper.getSearch(sci);
        return conCarts(carts);
    }
}
