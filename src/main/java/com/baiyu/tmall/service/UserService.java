package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.User;


public interface UserService {
    void insert(User user);
    User getByName(String username);
    void create(User user);
}
