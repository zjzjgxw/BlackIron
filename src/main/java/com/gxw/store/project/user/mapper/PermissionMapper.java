package com.gxw.store.project.user.mapper;



import com.gxw.store.project.user.dto.PermissionSearchParams;
import com.gxw.store.project.user.entity.Permission;
import com.gxw.store.project.user.entity.PermissionGroup;

import java.util.List;


public interface PermissionMapper {
     void create(Permission permission);

     /**
      * 搜索权限
      * @param searchParams
      * @return
      */
     List<Permission> getPermissions(PermissionSearchParams searchParams);


     Permission selectPermissionById(Long id);

     int updatePermission(Permission permission);

     int deletePermission(Long permissionId);

     /**
      * 检查权限是否被使用
      * @param permissionId
      * @return
      */
     int HasRoleUsedPermission(Long permissionId);

     /**
      * 根据权限组查找权限
      * @param groupId
      * @return
      */
     List<Permission> selectPermissionByGroupId(Long groupId);

     /**
      * 根据员工id 查找对应的权限
      * @param staffId
      * @return
      */
     List<Permission> selectPermissionByStaffId(Long staffId);

     /**
      * 根据权限id 查找对应的权限
      * @param adminId
      * @return
      */
     List<Permission> selectPermissionByAdminId(Long adminId);


     void createGroup(PermissionGroup permissionGroup);

     int updateGroup(PermissionGroup permissionGroup);

     List<PermissionGroup> getGroups(int type);
     /**
      * 删除权限组
      * @param id
      * @return
      */
     int deleteGroup(Long id);

     /**
      * 删除权限组中对应权限的groupId
      * @param groupId
      * @return
      */
     int deletePermissionGroupId(Long groupId);
}
