package com.baiyu.tmall.service.impl;

import com.baiyu.tmall.mapper.SysPermissionMapper;
import com.baiyu.tmall.mapper.SysPermissionRoleMapper;
import com.baiyu.tmall.mapper.SysRoleUserMapper;
import com.baiyu.tmall.pojo.SysPermission;
import com.baiyu.tmall.pojo.SysPermissionRole;
import com.baiyu.tmall.pojo.SysRoleUser;
import com.baiyu.tmall.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysPermissionImpl implements SysPermissionService {

    @Autowired(required = false)
    private SysPermissionMapper sysPermissionMapper;

    @Autowired(required = false)
    private SysPermissionRoleMapper sysPermissionRoleMapper;

    @Autowired(required = false)
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public Set<String> getNameByUserId(int userId) {
        Set<String> result = new HashSet<>();
        //获取角色id
        List<SysRoleUser> sysRoleUsers = sysRoleUserMapper.getByUserId(userId);
        if(sysRoleUsers.size() == 0) {
            return result;
        }
        List<Integer> roleIds = sysRoleUsers.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());

        //获取权限id
        List<SysPermissionRole> sysPermissionRoles = sysPermissionRoleMapper.getByRoleIds(roleIds);
        List<Integer> permissionIds = sysPermissionRoles.stream().map(SysPermissionRole::getPermissionId).collect(Collectors.toList());

        //获取权限数据
        List<SysPermission> sysPermissions = sysPermissionMapper.getByPermissionIds(permissionIds);


        for (SysPermission s : sysPermissions) {
            result.add(s.getName());
        }
        return result;
    }
}
