package com.baiyu.tmall;

import com.baiyu.tmall.mapper.BannerMapper;
import com.baiyu.tmall.pojo.Banner;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.pojo.GoodsIndex;
import com.baiyu.tmall.pojo.item.SearchGoodsItem;
import com.baiyu.tmall.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmallApplicationTests {

    @Autowired(required = false)
    private BannerMapper bannerMapper;

    @Autowired
    private GoodsService goodsService;

    @Test
    public void contextLoads() throws Exception {
        /*List<Integer> cateIds = Arrays.asList(1,2,3);
        List<GoodsIndex> goodsIndexList = goodsService.getIndexList(cateIds);
        System.out.println(goodsIndexList);*/
        SearchGoodsItem sgi = new SearchGoodsItem();
        sgi.setName("f");
        //sgi.setCateId(1);

        List<Goods> goods = goodsService.getSearch(sgi);
        System.out.println(goods);
    }

}
