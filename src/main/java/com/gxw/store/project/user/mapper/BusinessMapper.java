package com.gxw.store.project.user.mapper;


import com.gxw.store.project.user.dto.GroupPermissionRel;
import com.gxw.store.project.user.entity.business.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessMapper {
    void create(Business business);

    Business getBusiness(Long id);

    int updateBusiness(Business business);

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


    int updateRole(BusinessRole role);

    //获取角色详情
    BusinessRole getRoleInfo(@Param("roleId") Long roleId, @Param("businessId") Long businessId);

    //删除角色
    int deleteRoleById(Long roleId, Long businessId);

    //为角色添加权限
    void addRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") Long[] permissionIds);

    //删除某个角色下的所有权限
    int deleteRolePermissions(Long roleId);

    /**
     * 获取角色下的所有权限
     *
     * @param roleId
     * @param businessId
     * @return
     */
    List<GroupPermissionRel> getPermissionsOfRole(Long roleId, Long businessId);

    /**
     * 添加banner
     *
     * @param banner
     * @return
     */
    void addBanner(Banner banner);

    List<Banner> getBanners(Long businessId);


    /**
     * 修改banner
     *
     * @param banner
     * @return
     */
    int updateBanner(Banner banner);

    /**
     * 删除banner
     *
     * @param id
     * @param businessId
     * @return
     */
    int deleteBanner(Long id, Long businessId);


    /**
     * 添加Advertisement
     *
     * @param banner
     * @return
     */
    void addAdvertisement(Advertisement banner);

    List<Advertisement> getAdvertisements(Long businessId);

    /**
     * 修改Advertisement
     *
     * @param banner
     * @return
     */
    int updateAdvertisement(Advertisement banner);

    /**
     * 删除Advertisement
     *
     * @param id
     * @param businessId
     * @return
     */
    int deleteAdvertisement(Long id, Long businessId);


    /**
     * 添加导航项目
     *
     * @param navigation
     * @return
     */
    void addNavigation(Navigation navigation);

    List<Navigation> getNavigations(Long businessId);

    /**
     * 修改导航项
     *
     * @param navigation
     * @return
     */
    int updateNavigation(Navigation navigation);

    /**
     * 删除Navigation
     *
     * @param id
     * @param businessId
     * @return
     */
    int deleteNavigation(Long id, Long businessId);
}
