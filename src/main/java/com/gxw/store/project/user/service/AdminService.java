package com.gxw.store.project.user.service;


import com.gxw.store.project.user.VO.AdminVO;
import com.gxw.store.project.user.dto.AdminRoleRel;
import com.gxw.store.project.user.dto.AdminSearchParams;
import com.gxw.store.project.user.entity.admin.Admin;
import com.gxw.store.project.user.entity.admin.AdminRole;

import java.util.List;
import java.util.Set;

public interface AdminService {
    /**
     * 创建管理员
     *
     * @param admin
     * @return
     */
    Long create(Admin admin);

    /**
     * 获取admin详情
     *
     * @param id
     * @return
     */
    Admin getAdmin(Long id);

    /**
     * 根据账号信息搜索
     * @param account
     * @return
     */
    Admin getAdminByAccount(String account);

    /**
     * 查找管理员信息
     * @param adminSearchParams
     * @return
     */
    List<AdminVO> getAdmins(AdminSearchParams adminSearchParams);

    /**
     * 更新管理员信息
     *
     * @param admin
     * @return
     */
    Boolean updateAdmin(Admin admin);

    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    Boolean deleteAdmin(Long id);

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    Long createRole(AdminRole role);

    /**
     * 获取角色详情
     *
     * @param id
     * @return
     */
    AdminRole getRole(Long id);

    /**
     * 获取角色列表
     *
     * @return
     */
    List<AdminRole> getRoles();

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    Boolean updateRole(AdminRole role);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    Boolean deleteRole(Long id);


    /**
     * 为admin添加角色
     *
     * @param roleRelSet
     * @return
     */
    int addAdminRoles(Set<AdminRoleRel> roleRelSet);

    /**
     * 为admin 删除角色
     *
     * @param roleRelSet
     * @return
     */
    int deleteAdminRoles(Set<AdminRoleRel> roleRelSet);


    /**
     * 为角色添加权限
     *
     * @param roleId
     * @param permissions
     * @return
     */
    boolean saveRolePermissions(Long roleId, Long[] permissions);
}
