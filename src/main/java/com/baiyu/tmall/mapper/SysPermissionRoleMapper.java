package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.SysPermission;
import com.baiyu.tmall.pojo.SysPermissionRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionRoleMapper {
    List<SysPermissionRole> getByRoleIds(List<Integer> roleIds);
}
