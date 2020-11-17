package com.gxw.store.project.user.service.imp;


import com.gxw.store.project.user.entity.business.Banner;
import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.entity.business.BusinessDepartment;
import com.gxw.store.project.user.entity.business.BusinessRole;
import com.gxw.store.project.user.mapper.BusinessMapper;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessServiceImp implements BusinessService {

    @Autowired
    protected BusinessMapper businessMapper;

    @Override
    public Long create(Business business) {
        businessMapper.create(business);
        return business.getId();
    }

    @Override
    public Business getBusiness(Long id) {
        return businessMapper.getBusiness(id);
    }

    @Override
    public Long createDepartment(BusinessDepartment department) {
        businessMapper.createDepartment(department);
        return department.getId() ;
    }

    @Override
    public List<BusinessDepartment> getDepartments(Long businessId) {
        return businessMapper.getDepartments(businessId);
    }

    @Override
    public BusinessDepartment getDepartmentInfo(Long departmentId, Long businessId) {
        return businessMapper.getDepartmentInfo(departmentId,businessId);
    }

    @Override
    public int deleteDepartmentById(Long departmentId) {
        return businessMapper.deleteDepartmentById(departmentId);
    }

    @Override
    public Long createRole(BusinessRole role) {
        businessMapper.createRole(role);
        return role.getId();
    }

    @Override
    public List<BusinessRole> getRoles(Long businessId) {
        return businessMapper.getRoles(businessId);
    }

    @Override
    public BusinessRole getRoleInfo(Long roleId, Long businessId) {
        return businessMapper.getRoleInfo(roleId,businessId);
    }

    @Override
    public int deleteRoleById(Long roleId) {
        return businessMapper.deleteRoleById(roleId);
    }

    @Override
    @Transactional
    public boolean saveRolePermissions(Long roleId, Long[] permissions) {
        //先删除角色下所有的权限
        businessMapper.deleteRolePermissions(roleId);
        businessMapper.addRolePermission(roleId,permissions);
        return true;
    }

    @Override
    public Long addBanner(Banner banner) {
        businessMapper.addBanner(banner);
        return banner.getId();
    }

    @Override
    public List<Banner> getBanners(Long businessId) {
        return businessMapper.getBanners(businessId);
    }

    @Override
    public Boolean updateBanner(Banner banner) {
        int row = businessMapper.updateBanner(banner);
        return row != 0;
    }

    @Override
    public Boolean deleteBanner(Long id, Long businessId) {
        int row  = businessMapper.deleteBanner(id,businessId);
        return row != 0;
    }

}
