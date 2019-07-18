package com.baiyu.tmall.dao;

import com.baiyu.tmall.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsDAO extends ElasticsearchRepository<Goods, Integer> {
}
