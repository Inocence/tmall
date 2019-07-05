package com.baiyu.tmall.mapper;

import com.baiyu.tmall.pojo.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {
    List<SysRoleUser> getByUserId(int userId);
}
