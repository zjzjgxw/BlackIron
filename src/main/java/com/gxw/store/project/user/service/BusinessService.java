package com.gxw.store.project.user.service;


import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.entity.business.BusinessDepartment;
import com.gxw.store.project.user.entity.business.BusinessRole;

import java.util.List;

public interface BusinessService {
    /**
     * 创建商户
     * @param business
     * @return
     */
    Long create(Business business);

    /**
     * 获取商户信息
     * @param id
     * @return
     */
    Business getBusiness(Long id);

    /**
     * 创建部门
     * @param department
     * @return
     */
    Long createDepartment(BusinessDepartment department);

    /**
     * 获取商户所有部门
     * @param businessId  商户id
     * @return
     */
    List<BusinessDepartment> getDepartments(Long businessId);


    /**
     * 获取某个部门的信息
     * @param departmentId
     * @param businessId
     * @return
     */
    BusinessDepartment getDepartmentInfo(Long departmentId, Long businessId);


    /**
     * 删除部门
     * @param departmentId 部门id
     * @return
     */
    int deleteDepartmentById(Long departmentId);

    /**
     * 创建角色
     * @param role
     * @return
     */
    Long createRole(BusinessRole role);

    /**
     * 创建角色
     * @param businessId
     * @return
     */
    List<BusinessRole> getRoles(Long businessId);

    /**
     * 获取角色详情
     * @param roleId
     * @param businessId
     * @return
     */
    BusinessRole getRoleInfo(Long roleId, Long businessId);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int deleteRoleById(Long roleId);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissions
     * @return
     */
    boolean saveRolePermissions(Long roleId, Long[] permissions);
}
