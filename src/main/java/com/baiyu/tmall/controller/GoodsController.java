package com.baiyu.tmall.controller;

import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoodsController {
    @Autowired(required = false)
    private GoodsService goodsService;

    @GetMapping("/goods/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("goods", goodsService.getOne(id));
        return "goods/detail";
    }

    @RequestMapping("/goodsSearch")
    public String list(HttpServletRequest request, Model model) {
        return "goods/list";
    }
}
