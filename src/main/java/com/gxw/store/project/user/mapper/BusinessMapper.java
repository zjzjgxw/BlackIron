package com.gxw.store.project.user.mapper;


import com.gxw.store.project.user.entity.business.Banner;
import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.entity.business.BusinessDepartment;
import com.gxw.store.project.user.entity.business.BusinessRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessMapper {
     void create(Business business);

     Business getBusiness(Long id);

     //创建部门
     void createDepartment(BusinessDepartment businessDepartment);

     //获取商户下所有部门
     List<BusinessDepartment> getDepartments(Long businessId);

     //获取部门信息
     BusinessDepartment getDepartmentInfo(@Param("departmentId") Long departmentId, @Param("businessId") Long businessId);

     //删除部门
     int deleteDepartmentById(Long departmentId);

     //创建角色
     void createRole(BusinessRole businessRole);

     //获取商户下所有角色
     List<BusinessRole> getRoles(Long businessId);

     //获取角色详情
     BusinessRole getRoleInfo(@Param("roleId") Long roleId, @Param("businessId") Long businessId);

     //删除角色
     int deleteRoleById(Long roleId);

     //为角色添加权限
     void addRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") Long[] permissionIds);

     //删除某个角色下的所有权限
     int deleteRolePermissions(Long roleId);

     /**
      * 添加banner
      * @param banner
      * @return
      */
     void addBanner(Banner banner);

     List<Banner> getBanners(Long businessId);
}
