package com.baiyu.tmall;

import com.baiyu.tmall.mapper.BannerMapper;
import com.baiyu.tmall.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmallApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void contextLoads() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("929254992@qq.com");
        message.setTo("929254992@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        javaMailSender.send(message);
    }

}
