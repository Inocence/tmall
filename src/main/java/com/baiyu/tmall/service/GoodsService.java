package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.GoodsIndex;
import com.baiyu.tmall.pojo.vo.GoodsVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GoodsService {
    Goods getOne(int id);
    List<Goods> getList(Goods goods);
    List<Goods> getSearch(GoodsVo igi);
    Page<Goods> getEsSearch(GoodsVo igi);
    List<GoodsIndex> getIndexList(List<Integer> cateIds);
}
