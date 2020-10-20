package com.gxw.store.project.user.service;


import com.gxw.store.project.user.dto.StaffDepartmentRel;
import com.gxw.store.project.user.dto.StaffRoleRel;
import com.gxw.store.project.user.entity.business.Staff;

import java.util.Set;

public interface StaffService {
    Long create(Staff staff);

    Staff getStaff(Long id);

    Staff getStaffByAccount(String account);

    /**
     * 新增用户所属部门
     * @param staffDepartmentRelSet
     * @return
     */
    int addDepartments(Set<StaffDepartmentRel> staffDepartmentRelSet);

    /**
     * 删除用户所属部门
     * @param staffDepartmentRelSet
     * @return
     */
    int deleteDepartments(Set<StaffDepartmentRel> staffDepartmentRelSet);


    /**
     * 新增用户所属角色
     * @param staffRoleRelSet
     * @return
     */
    int addRoles(Set<StaffRoleRel> staffRoleRelSet);

    /**
     * 删除用户所属角色
     * @param staffRoleRelSet
     * @return
     */
    int deleteRoles(Set<StaffRoleRel> staffRoleRelSet);

}
