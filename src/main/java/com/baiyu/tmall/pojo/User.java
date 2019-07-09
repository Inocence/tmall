package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String verify;
    private int status;
    private String createTime;
}
