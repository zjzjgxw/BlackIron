package com.gxw.store.project.user.service.imp;


import com.gxw.store.project.user.dto.PermissionSearchParams;
import com.gxw.store.project.user.entity.Permission;
import com.gxw.store.project.user.entity.PermissionGroup;
import com.gxw.store.project.user.mapper.PermissionMapper;
import com.gxw.store.project.user.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImp implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public Long create(Permission permission) {
        permissionMapper.create(permission);
        return permission.getId();
    }

    @Override
    public List<Permission> getPermissions(PermissionSearchParams searchParams) {
        return permissionMapper.getPermissions(searchParams);
    }

    @Override
    public boolean updatePermission(Permission permission) {
        permissionMapper.updatePermission(permission);
        return true;
    }

    @Override
    public Permission selectPermissionById(Long id) {
        return permissionMapper.selectPermissionById(id);
    }

    @Override
    public boolean deletePermission(Long id) {
        int usedNum = permissionMapper.HasRoleUsedPermission(id);
        if (usedNum != 0) {
            return false;
        }
        permissionMapper.deletePermission(id);
        return true;
    }

    @Override
    public Long createGroup(PermissionGroup permissionGroup) {
        permissionMapper.createGroup(permissionGroup);
        return permissionGroup.getId();
    }

    @Override
    public boolean updateGroup(PermissionGroup permissionGroup) {
        permissionMapper.updateGroup(permissionGroup);
        return true;
    }

    @Override
    public List<PermissionGroup> getGroups(int type) {
        return permissionMapper.getGroups(type);
    }

    @Override
    @Transactional
    public boolean deleteGroup(Long groupId) {
        permissionMapper.deleteGroup(groupId);
        permissionMapper.deletePermissionGroupId(groupId);
        return true;
    }

    @Override
    public List<Permission> selectPermissionsByStaffId(Long staffId) {
        return permissionMapper.selectPermissionByStaffId(staffId);
    }

    @Override
    public List<Permission> selectPermissionsByAdminId(Long adminId) {
        return permissionMapper.selectPermissionByAdminId(adminId);
    }

}
