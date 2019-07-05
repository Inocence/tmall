package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.User;
import com.baiyu.tmall.pojo.item.SearchUserItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user);
    User getByName(String username);
    List<User> getSearch(User user);
}
