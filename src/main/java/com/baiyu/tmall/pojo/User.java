package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String verify;
    private int status;
    private String createTime;
}
