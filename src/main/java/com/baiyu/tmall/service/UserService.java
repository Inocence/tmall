package com.baiyu.tmall.service;

import com.baiyu.tmall.pojo.User;
import com.baiyu.tmall.util.Result;


public interface UserService {
    void insert(User user);
    User getByName(String username);
    Result create(User user);
    boolean verify(String verify);
    void sendEmail(String email, String code);
}
