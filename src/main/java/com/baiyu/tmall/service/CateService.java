package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.vo.CateVo;

import java.util.List;

public interface CateService {
    List<Cate> getAll();
    List<Cate> getSearch(CateVo sci);
}
