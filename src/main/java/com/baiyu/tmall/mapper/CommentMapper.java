package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insert(Comment comment);
    Comment getOne(int id);
    List<Comment> getSearch(Comment comment);
}
