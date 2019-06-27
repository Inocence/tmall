package com.baiyu.tmall.controller;

import com.baiyu.tmall.pojo.Banner;
import com.baiyu.tmall.pojo.Cate;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.GoodsIndex;
import com.baiyu.tmall.pojo.item.SearchCatesItem;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;
import com.baiyu.tmall.service.BannerService;
import com.baiyu.tmall.service.CateService;
import com.baiyu.tmall.service.GoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String Search(Model model, @Param("keyword") String keyword, @Param("cateId") String cateId) {
        SearchGoodsItem sgi = new SearchGoodsItem();
        if(keyword!=null) {
            sgi.setName(keyword);
        }
        if(cateId!=null) {
            sgi.setCateId(Integer.parseInt(cateId));
        }

        List<Goods> goods = goodsService.getSearch(sgi);
        model.addAttribute("search", goods);

        return "index/search";
    }
}
