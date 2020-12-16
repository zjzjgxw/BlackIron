package com.gxw.store.project.user.service.imp;


import com.gxw.store.project.common.utils.FileUtils;
import com.gxw.store.project.user.dto.GroupPermissionRel;
import com.gxw.store.project.user.entity.business.*;
import com.gxw.store.project.user.mapper.BusinessMapper;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusinessServiceImp implements BusinessService {

    @Resource
    protected BusinessMapper businessMapper;

    @Override
    public Long create(Business business) {
        businessMapper.create(business);
        return business.getId();
    }

    @Override
    public Business getBusiness(Long id) {
        Business business = businessMapper.getBusiness(id);
        business.setLogoPath(business.getLogoImg());
        if(!business.getLogoImg().isEmpty()){
            business.setLogoImg(FileUtils.getPath(business.getLogoImg()));
        }
        return business;
    }

    @Override
    public boolean updateBusiness(Business business) {
        int row = businessMapper.updateBusiness(business);
        return row > 0;
    }

    @Override
    public Long createDepartment(BusinessDepartment department) {
        businessMapper.createDepartment(department);
        return department.getId();
    }

    @Override
    public List<BusinessDepartment> getDepartments(Long businessId) {
        return businessMapper.getDepartments(businessId);
    }

    @Override
    public BusinessDepartment getDepartmentInfo(Long departmentId, Long businessId) {
        return businessMapper.getDepartmentInfo(departmentId, businessId);
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
    public boolean updateRole(BusinessRole businessRole) {
        int row = businessMapper.updateRole(businessRole);
        return row > 0;
    }

    @Override
    public BusinessRole getRoleInfo(Long roleId, Long businessId) {
        return businessMapper.getRoleInfo(roleId, businessId);
    }

    @Override
    public boolean deleteRoleById(Long roleId, Long businessId) {
        int row = businessMapper.deleteRoleById(roleId, businessId);
        return row > 0;
    }

    @Override
    @Transactional
    public boolean saveRolePermissions(Long roleId, Long[] permissions) {
        //先删除角色下所有的权限
        businessMapper.deleteRolePermissions(roleId);
        businessMapper.addRolePermission(roleId, permissions);
        return true;
    }

    @Override
    public List<GroupPermissionRel> getPermissionsOfRole(Long roleId, Long businessId) {
        return businessMapper.getPermissionsOfRole(roleId, businessId);
    }

    @Override
    public Long addAdvertisement(Advertisement banner) {
        businessMapper.addAdvertisement(banner);
        return banner.getId();
    }

    @Override
    public List<Advertisement> getAdvertisements(Long businessId) {
        List<Advertisement> advertisements = businessMapper.getAdvertisements(businessId);
        for (Advertisement advertisement : advertisements) {
            advertisement.setImgPath(advertisement.getImgUrl());
            advertisement.setImgUrl(FileUtils.getPath(advertisement.getImgUrl()));
        }
        return advertisements;
    }

    @Override
    public Boolean updateAdvertisement(Advertisement advertisement) {
        int row = businessMapper.updateAdvertisement(advertisement);
        return row != 0;
    }

    @Override
    public Boolean deleteAdvertisement(Long id, Long businessId) {
        int row = businessMapper.deleteAdvertisement(id, businessId);
        return row != 0;
    }

    @Override
    public Long addBanner(Banner banner) {
        businessMapper.addBanner(banner);
        return banner.getId();
    }

    @Override
    public List<Banner> getBanners(Long businessId) {
        List<Banner> banners = businessMapper.getBanners(businessId);
        for (Banner banner : banners) {
            banner.setImgPath(banner.getImgUrl());
            banner.setImgUrl(FileUtils.getPath(banner.getImgUrl()));
        }
        return banners;
    }

    @Override
    public Boolean updateBanner(Banner banner) {
        int row = businessMapper.updateBanner(banner);
        return row != 0;
    }

    @Override
    public Boolean deleteBanner(Long id, Long businessId) {
        int row = businessMapper.deleteBanner(id, businessId);
        return row != 0;
    }

}
