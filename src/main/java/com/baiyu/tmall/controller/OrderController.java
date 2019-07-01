package com.baiyu.tmall.controller;

import com.baiyu.tmall.mapper.ItemMapper;
import com.baiyu.tmall.pojo.Cart;
import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.pojo.Orders;
import com.baiyu.tmall.pojo.item.SearchCartItem;
import com.baiyu.tmall.service.CartService;
import com.baiyu.tmall.service.ItemService;
import com.baiyu.tmall.service.OrdersService;
import com.baiyu.tmall.util.Result;
import com.baiyu.tmall.util.Tool;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/cart")
    public String cart(Model model) {
        List<Cart> carts = cartService.getAll();
        model.addAttribute("carts", carts);

        return "orders/cart";
    }

    @GetMapping("/cartDel")
    public String cartDel(Cart cart) {
        cartService.delete(cart);
        return "redirect:/cart";
    }

    @PostMapping("/cart")
    @ResponseBody
    public Result<Cart> cart(Cart cart) {
        cart.setCreateTime(Tool.timeFormat());
        cart.setUserId(1);
        cartService.insert(cart);

        return Result.success(cart);
    }

    @GetMapping("/book")
    public String book(@Param("cartIds") String cartIds, Model model) {
        SearchCartItem sci = new SearchCartItem();
        String[] cartStrIds = cartIds.split(",");

        List<Integer> cartIntIds = new ArrayList<>();
        for (String s : cartStrIds) {
            cartIntIds.add(Integer.parseInt(s));
        }
        sci.setCartIds(cartIntIds);
        List<Cart> carts = cartService.getSearch(sci);
        int total_num = 0;
        int total_price = 0;
        for (Cart cart : carts) {
            total_num += cart.getNum();
            total_price += cart.getGoods().getPrice() * cart.getNum();
        }

        model.addAttribute("carts", carts);
        model.addAttribute("total_num", total_num);
        model.addAttribute("total_price", total_price);

        return "orders/book";
    }

    @PostMapping("/book")
    public String book(SearchCartItem sci) {
        StringBuffer str = new StringBuffer();
        for (Cart cart : sci.getCarts()) {
            cartService.update(cart);
            str.append("," + cart.getCartId());
        }

        return "redirect:/book?cartIds=" + str.substring(1);
    }

    @PostMapping("/orders")
    public String orders(SearchCartItem sci) {
        int ordersId = ordersService.orders(sci);
        return "redirect:/orders/" + ordersId;
    }

    @GetMapping("orders/{id}")
    public String orders(@PathVariable("id") int id, Model model) {
        Orders orders = ordersService.getOne(id);
        System.out.println(orders);
        model.addAttribute("orders", orders);
        return "orders/alipay";
    }

    @PostMapping("alipay")
    public String alipay(Orders orders) {
        orders.setStatus(2);
        orders.setPayTime(Tool.timeFormat());
        orders.setStatusTime(Tool.timeFormat());
        System.out.println(orders);
        ordersService.update(orders);
        return "redirect:/cart";
    }

    @GetMapping("ordersList")
    public String list(Model model) {
        Orders all = new Orders();
        //all.setStatus(1);
        List<Item> allList = itemService.getSearch(all);

        Orders unpay = new Orders();
        unpay.setStatus(1);
        List<Item> unpayList = itemService.getSearch(unpay);

        Orders uncomment = new Orders();
        uncomment.setStatus(2);
        List<Item> uncommentList = itemService.getSearch(uncomment);

        model.addAttribute("all", allList);
        model.addAttribute("unpay", unpayList);
        model.addAttribute("uncomment", uncommentList);

        return "orders/list";
    }
}
