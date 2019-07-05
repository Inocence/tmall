package com.baiyu.tmall.controller;

import com.baiyu.tmall.pojo.Comment;
import com.baiyu.tmall.pojo.Goods;
import com.baiyu.tmall.service.CommentService;
import com.baiyu.tmall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GoodsController {
    @Autowired(required = false)
    private GoodsService goodsService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/goods/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("goods", goodsService.getOne(id));
        Comment comment = new Comment();
        comment.setGoodsId(id);
        List<Comment> comments = commentService.getSearch(comment);
        model.addAttribute("comments", comments);

        System.out.println(comments);
        return "goods/detail";
    }

    @RequestMapping("/goodsSearch")
    public String list(HttpServletRequest request, Model model) {
        return "goods/list";
    }
}
