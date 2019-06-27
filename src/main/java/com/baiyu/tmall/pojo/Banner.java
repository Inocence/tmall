package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Banner {
    private int bannerId;
    private String img;
    private String url;
    private int status;
    private String createTime;
}
