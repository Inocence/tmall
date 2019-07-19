package com.baiyu.tmall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baiyu.tmall.dao.GoodsDAO;
import com.baiyu.tmall.mapper.CateMapper;
import com.baiyu.tmall.mapper.GoodsMapper;
import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.GoodsIndex;
import com.baiyu.tmall.pojo.vo.CateVo;
import com.baiyu.tmall.pojo.vo.GoodsVo;
import com.baiyu.tmall.service.GoodsService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "goods")
public class GoodsServiceImpl implements GoodsService {
    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Autowired(required = false)
    private CateMapper cateMapper;

    @Autowired(required = false)
    private GoodsDAO goodsDAO;

    @Cacheable(key = "'goods_' + #p0")
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
    public List<Goods> getSearch(GoodsVo igi){
        return goodsMapper.getSearch(igi);
    }

    @Override
    public Page<Goods> getEsSearch(GoodsVo igi, Pageable pageable) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.wildcardQuery("name.keyword", "*" + igi.getName() + "*"))
                .withPageable(pageable);

        Page<Goods> page = goodsDAO.search(builder.build());
        System.out.println(page.getContent());
        return page;
    }


    @Override
    public List<GoodsIndex> getIndexList(List<Integer> cateIds){
        List<GoodsIndex> goodsIndexs = new ArrayList<>();
        GoodsVo sgi = new GoodsVo();
        sgi.setTotal(10);

        //查询分类名字
        CateVo sci = new CateVo();
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
