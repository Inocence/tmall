package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    List<SysPermission> getByPermissionIds(List<Integer> permissionIds);
}
