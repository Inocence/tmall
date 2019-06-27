package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    public List<Banner> getAll();
}
