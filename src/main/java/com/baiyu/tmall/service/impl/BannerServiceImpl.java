package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.BannerMapper;
import com.baiyu.tmall.pojo.Banner;
import com.baiyu.tmall.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired(required = false)
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> getAll(){
        return bannerMapper.getAll();
    }
}
