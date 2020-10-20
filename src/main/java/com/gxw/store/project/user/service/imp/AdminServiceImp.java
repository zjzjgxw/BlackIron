package com.gxw.store.project.user.service.imp;


import com.gxw.store.project.common.utils.Md5Utils;
import com.gxw.store.project.common.utils.StringUtils;
import com.gxw.store.project.common.utils.exception.HasExistException;
import com.gxw.store.project.user.VO.AdminVO;
import com.gxw.store.project.user.dto.AdminRoleRel;
import com.gxw.store.project.user.dto.AdminSearchParams;
import com.gxw.store.project.user.entity.admin.Admin;
import com.gxw.store.project.user.entity.admin.AdminRole;
import com.gxw.store.project.user.mapper.AdminMapper;
import com.gxw.store.project.user.service.AdminService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class AdminServiceImp implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Long create(Admin admin) {
        Admin has = adminMapper.getAdminByAccount(admin.getAccount());
        if(has != null){
            throw new HasExistException("账号已经存在");
        }
        admin.setPassword(Md5Utils.hash(admin.getPassword()));
        adminMapper.create(admin);
        return admin.getId();
    }

    @Override
    public Admin getAdmin(Long id) {
        return adminMapper.getAdminById(id);
    }

    @Override
    public Admin getAdminByAccount(String account) {
        return adminMapper.getAdminByAccount(account);
    }

    @Override
    public List<AdminVO> getAdmins(AdminSearchParams adminSearchParams) {
        List<Admin> admins = adminMapper.getAdmins(adminSearchParams);
        List<AdminVO> adminVOList = new LinkedList<>();
        for (Admin admin:admins){
            adminVOList.add(new AdminVO(admin));
        }
        return adminVOList;
    }

    @Override
    public Boolean updateAdmin(Admin admin) {
        if(admin.getId() == null){
            return false;
        }
        if(!StringUtils.isEmpty(admin.getPassword())){
            admin.setPassword(Md5Utils.hash(admin.getPassword()));
        }
        adminMapper.updateAdmin(admin);
        return true;
    }

    @Override
    public Boolean deleteAdmin(Long id) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setDeleteFlag(1);
        adminMapper.updateAdmin(admin);
        return true;
    }

    @Override
    public Long createRole(AdminRole role) {
        adminMapper.createRole(role);
        return role.getId();
    }

    @Override
    public AdminRole getRole(Long id) {
        return adminMapper.getRole(id);
    }

    @Override
    public List<AdminRole> getRoles() {
        return adminMapper.getRoles();
    }

    @Override
    public Boolean updateRole(AdminRole role) {
        if(role.getId() == null){
            return false;
        }
        adminMapper.updateRole(role);
        return true;
    }

    @Override
    public Boolean deleteRole(Long id) {
        AdminRole role = new AdminRole();
        role.setId(id);
        role.setDeleteFlag(1);
        adminMapper.updateRole(role);
        return true;
    }

    @Override
    public int addAdminRoles(Set<AdminRoleRel> roleRelSet) {
        //TODO 可以验证下角色是否存在，有效
        return adminMapper.addAdminRoles(roleRelSet);
    }

    @Override
    public int deleteAdminRoles(Set<AdminRoleRel> roleRelSet) {
        return adminMapper.deleteAdminRoles(roleRelSet);
    }

    @Override
    @Transactional
    public boolean saveRolePermissions(Long roleId, Long[] permissions) {
        //先删除角色下所有的权限
        adminMapper.deleteRolePermissions(roleId);
        adminMapper.addRolePermission(roleId,permissions);
        return true;
    }
}
