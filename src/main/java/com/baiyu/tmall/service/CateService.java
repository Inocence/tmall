package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.item.SearchCatesItem;

import java.util.List;

public interface CateService {
    List<Cate> getAll();
    List<Cate> getSearch(SearchCatesItem sci);
}
