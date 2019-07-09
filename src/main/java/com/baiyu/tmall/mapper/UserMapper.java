package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.User;
import com.baiyu.tmall.pojo.item.SearchUserItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user);
    void update(User user);
    User getByName(String username);
    User getByEmail(String email);
    User getByVerify(String verify);
    List<User> getSearch(User user);
}
