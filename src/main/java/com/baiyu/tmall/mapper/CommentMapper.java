package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    void insert(Comment comment);
    Comment getOne(int id);
}
