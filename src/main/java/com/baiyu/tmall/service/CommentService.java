package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.Comment;

import java.util.List;


public interface CommentService {
    void insert(Comment comment);
    Comment getOne(int id);
    void create(Comment comment);
    List<Comment> getSearch(Comment comment);
}
