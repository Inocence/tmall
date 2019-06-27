package com.baiyu.tmall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baiyu.tmall.mapper.CateMapper;
import com.baiyu.tmall.mapper.GoodsMapper;
import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.GoodsIndex;
import com.baiyu.tmall.pojo.item.SearchCatesItem;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;
import com.baiyu.tmall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Autowired(required = false)
    private CateMapper cateMapper;

    @Override
    public Goods getOne(int id) {
        Goods goods = goodsMapper.getOne(id);
        goods.setImgList(JSONObject.parseArray(goods.getImg(), String.class));
        return goods;
    }

    @Override
    public List<Goods> getList(Goods goods){
        return goodsMapper.getList(goods);
    }

    @Override
    public List<Goods> getSearch(SearchGoodsItem igi){
        return goodsMapper.getSearch(igi);
    }

    @Override
    public List<GoodsIndex> getIndexList(List<Integer> cateIds){
        List<GoodsIndex> goodsIndexs = new ArrayList<>();
        SearchGoodsItem sgi = new SearchGoodsItem();
        sgi.setTotal(10);

        //查询分类名字
        SearchCatesItem sci = new SearchCatesItem();
        sci.setCateIds(cateIds);
        List<Cate> cates = cateMapper.getSearch(sci);
        Map<Integer, List<Cate>> catesMap = cates.stream().collect(Collectors.groupingBy(Cate::getCateId));

        //组装查询数据
        GoodsIndex goodsIndex;
        for (Integer i : cateIds) {
            goodsIndex = new GoodsIndex();
            goodsIndex.setCate(catesMap.get(i).get(0));
            sgi.setCateId(i);
            goodsIndex.setGoods(goodsMapper.getSearch(sgi));
            goodsIndexs.add(goodsIndex);
            /*if(goodsListTmp != null) {
                Iterator<Goods> ig = goodsListTmp.iterator();
                //组装分类数据
                while (ig.hasNext()) {
                    goodsTmp = ig.next();
                    if(goodsTmp != null && catesMap.containsKey(goodsTmp.getCateId())) {
                        ig.next().setCate(catesMap.get(goodsTmp.getCateId()).get(0));
                    }
                }
            }*/
        }
        return goodsIndexs;
    }
}
