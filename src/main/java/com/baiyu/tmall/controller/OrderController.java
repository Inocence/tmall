package com.baiyu.tmall.controller;

import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.service.CartService;
import com.baiyu.tmall.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model) {
        List<Cart> carts = cartService.getAll();
        model.addAttribute("carts", carts);

        return "order/cart";
    }

    @GetMapping("/cartDel")
    public String cartDel(@ModelAttribute Cart cart) {
        cartService.delete(cart);
        return "redirect:/cart";
    }

    @PostMapping("/cart")
    @ResponseBody
    public Result<Cart> cart(@ModelAttribute  Cart cart) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        cart.setCreateTime(sdf.format(date));
        cart.setUserId(1);
        cartService.insert(cart);

        return Result.success(cart);
    }
}
