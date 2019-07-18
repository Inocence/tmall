package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.vo.CateVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CateMapper {
    List<Cate> getAll();
    List<Cate> getSearch(CateVo sci);
}
