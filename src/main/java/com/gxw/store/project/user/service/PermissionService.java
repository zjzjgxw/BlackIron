package com.gxw.store.project.user.service;



import com.gxw.store.project.user.dto.PermissionSearchParams;
import com.gxw.store.project.user.entity.Permission;
import com.gxw.store.project.user.entity.PermissionGroup;

import java.util.List;


public interface PermissionService {
    Long create(Permission permission);

    List<Permission> getPermissions(PermissionSearchParams searchParams);

    boolean updatePermission(Permission permission);

    Permission selectPermissionById(Long id);

    boolean deletePermission(Long id);

    Long createGroup(PermissionGroup permissionGroup);

    boolean updateGroup(PermissionGroup permissionGroup);

    List<PermissionGroup> getGroups(int type);

    boolean deleteGroup(Long id);

    /**
     * 查看某个员工的权限
     * @param staffId
     * @return
     */
    List<Permission> selectPermissionsByStaffId(Long staffId);

    /**
     * 查看某个管理员的权限
     * @param adminId
     * @return
     */
    List<Permission> selectPermissionsByAdminId(Long adminId);

}
