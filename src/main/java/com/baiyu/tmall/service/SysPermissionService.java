package com.baiyu.tmall.service;

import java.util.Set;


public interface SysPermissionService {
    Set<String> getNameByUserId(int userId);
}
