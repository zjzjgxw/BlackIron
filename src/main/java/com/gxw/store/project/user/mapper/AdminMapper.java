package com.gxw.store.project.user.mapper;


import com.gxw.store.project.user.dto.AdminRoleRel;
import com.gxw.store.project.user.dto.AdminSearchParams;
import com.gxw.store.project.user.entity.admin.Admin;
import com.gxw.store.project.user.entity.admin.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface AdminMapper {
    void create(Admin admin);

    Admin getAdminByAccount(String account);

    List<Admin> getAdmins(AdminSearchParams adminSearchParams);

    Admin getAdminById(Long id);

    int updateAdmin(Admin admin);

    void createRole(AdminRole role);

    AdminRole getRole(Long id);

    List<AdminRole> getRoles();

    int updateRole(AdminRole role);

    int addAdminRoles(@Param("set") Set<AdminRoleRel> roleRelSet);

    int deleteAdminRoles(@Param("set") Set<AdminRoleRel> roleRelSet);

    //为角色添加权限
    void addRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") Long[] permissionIds);

    //删除某个角色下的所有权限
    int deleteRolePermissions(Long roleId);
}
