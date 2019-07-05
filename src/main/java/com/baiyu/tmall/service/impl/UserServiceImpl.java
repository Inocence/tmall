package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.UserMapper;
import com.baiyu.tmall.pojo.User;
import com.baiyu.tmall.service.UserService;
import com.baiyu.tmall.util.Tool;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Value("tmall.encrypt")
    private String encrypt;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void create(User user) {
        String password = new SimpleHash("md5", user.getPassword(), ByteSource.Util.bytes(encrypt), 1).toHex();
        user.setPassword(password);
        user.setCreateTime(Tool.timeFormat());
        user.setStatus(1);
        userMapper.insert(user);
    }

    @Override
    public User getByName(String username) {
        return userMapper.getByName(username);
    }

}
