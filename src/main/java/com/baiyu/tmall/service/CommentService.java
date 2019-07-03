package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Comment;


public interface CommentService {
    void insert(Comment comment);
    Comment getOne(int id);
    void create(Comment comment);
}
