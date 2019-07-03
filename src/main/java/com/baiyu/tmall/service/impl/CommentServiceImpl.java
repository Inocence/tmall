package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.CommentMapper;
import com.baiyu.tmall.mapper.ItemMapper;
import com.baiyu.tmall.mapper.OrdersMapper;
import com.baiyu.tmall.pojo.Comment;
import com.baiyu.tmall.pojo.Item;
import com.baiyu.tmall.pojo.Orders;
import com.baiyu.tmall.service.CommentService;
import com.baiyu.tmall.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Autowired(required = false)
    private OrdersMapper ordersMapper;

    @Override
    public Comment getOne(int id) {
        return commentMapper.getOne(id);
    }

    @Override
    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public void create(Comment comment) {
        comment.setUserId(1);
        comment.setCreateTime(Tool.timeFormat());
        insert(comment);

        Item item = new Item();
        item.setItemId(comment.getItemId());
        item.setStatus(3);
        itemMapper.update(item);

        int count = itemMapper.count(item);
        item.setStatus(0);
        int count_all = itemMapper.count(item);
        if(count == count_all) {
            Orders orders = new Orders();
            orders.setOrdersId(comment.getOrdersId());
            orders.setStatus(3);
            orders.setStatusTime(Tool.timeFormat());
            ordersMapper.update(orders);
        }
    }
}
