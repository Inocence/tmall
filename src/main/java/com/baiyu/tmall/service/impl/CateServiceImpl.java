package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.CateMapper;
import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.item.SearchCatesItem;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;
import com.baiyu.tmall.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateServiceImpl implements CateService {

    @Autowired(required = false)
    private CateMapper cateMapper;

    @Override
    public List<Cate> getAll(){
        return cateMapper.getAll();
    }

    @Override
    public List<Cate> getSearch(SearchCatesItem sci){
        return cateMapper.getSearch(sci);
    }
}
