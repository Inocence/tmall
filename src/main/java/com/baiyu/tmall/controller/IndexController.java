package com.baiyu.tmall.controller;

import com.baiyu.tmall.pojo.Banner;
import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.GoodsIndex;
import com.baiyu.tmall.pojo.vo.GoodsVo;
import com.baiyu.tmall.service.BannerService;
import com.baiyu.tmall.service.CateService;
import com.baiyu.tmall.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class IndexController {

    @Autowired(required = false)
    private BannerService bannerService;

    @Autowired(required = false)
    private CateService cateService;

    @Autowired(required = false)
    private GoodsService goodsService;

    @RequestMapping("")
    public String index(Model model) {
        List<Banner> banners = bannerService.getAll();
        model.addAttribute("banners", banners);

        List<Cate> cates = cateService.getAll();
        model.addAttribute("cates", cates);

        List<Integer> cateIds = Arrays.asList(1,2,3);
        List<GoodsIndex> goodsIndexs = goodsService.getIndexList(cateIds);
        model.addAttribute("goodsIndexs", goodsIndexs);

        return "index/index";
    }

    @RequestMapping("/search")
    public String Search(
            Model model,
            @Param("keyword") String keyword,
            @Param("cateId") String cateId,
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize
            ) {
        GoodsVo sgi = new GoodsVo();
        if(keyword!=null) {
            sgi.setName(keyword);
        }
        if(cateId!=null) {
            sgi.setCateId(Integer.parseInt(cateId));
        }

        Page<Goods> search = goodsService.getEsSearch(sgi);
        model.addAttribute("search", search);
        return "index/search";
    }
}
