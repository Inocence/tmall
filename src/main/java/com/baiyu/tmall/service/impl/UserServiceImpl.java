package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.UserMapper;
import com.baiyu.tmall.pojo.User;
import com.baiyu.tmall.service.UserService;
import com.baiyu.tmall.util.Result;
import com.baiyu.tmall.util.Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${tmall.encrypt}")
    private String encrypt;

    @Value("${spring.mail.username}")
    private String email;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public Result create(User user) {
        User one = userMapper.getByEmail(user.getEmail());
        User name = userMapper.getByName(user.getUsername());
        if(one!=null && one.getStatus() == 2) {
            return Result.error(one, "邮箱已存在");
        }
        if(name!=null && name.getStatus() == 2) {
            return Result.error(name, "用户名已存在");
        }

        String code = new SecureRandomNumberGenerator().nextBytes().toHex();
        log.info(encrypt);
        String password = new SimpleHash("md5", user.getPassword(), ByteSource.Util.bytes(encrypt), 1).toHex();

        if(one == null) {
            user.setPassword(password);
            user.setCreateTime(Tool.timeFormat());
            user.setVerify(code);
            user.setStatus(1);
            userMapper.insert(user);
            return Result.success(user);
        } else{
            one.setVerify(code);
            one.setUsername(user.getUsername());
            one.setPassword(password);
            userMapper.update(one);
            return Result.success(one);
        }
    }

    @Override
    public boolean verify(String verify) {
        System.out.println(verify);
        if(verify.length() == 0) {
            return false;
        }
        User user = userMapper.getByVerify(verify);
        System.out.println(user);
        if(user == null) {
            return false;
        }
        user.setStatus(2);
        userMapper.update(user);
        return true;
    }

    @Override
    public User getByName(String username) {
        return userMapper.getByName(username);
    }

    @Override
    @Async
    public void sendEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo("929254992@qq.com");
        message.setSubject("Tmall邮箱验证");
        message.setText("请点击链接通过验证http://127.0.0.1:8080/verify?code=" + code);

        javaMailSender.send(message);
    }

}
