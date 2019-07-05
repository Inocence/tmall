package com.baiyu.tmall.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysPermission {
    private int permissionId;
    private String name;
    private String url;
}
