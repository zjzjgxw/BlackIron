package com.gxw.store.project.user.service.imp;

import com.gxw.store.project.common.utils.Md5Utils;
import com.gxw.store.project.common.utils.exception.HasExistException;
import com.gxw.store.project.user.dto.StaffDepartmentRel;
import com.gxw.store.project.user.dto.StaffRoleRel;
import com.gxw.store.project.user.entity.business.Staff;
import com.gxw.store.project.user.mapper.StaffMapper;
import com.gxw.store.project.user.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StaffServiceImp implements StaffService {
    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Long create(Staff staff) {
        Staff hasOne = staffMapper.getStaffByAccount(staff.getAccount());
        if (hasOne != null) {
            throw new HasExistException("账号已经存在");
        }

        staff.setPassword(Md5Utils.hash(staff.getPassword()));
        staffMapper.create(staff);
        return staff.getId();
    }

    @Override
    public Staff getStaff(Long id) {
        return staffMapper.getStaff(id);
    }

    @Override
    public Staff getStaffByAccount(String account) {
        return staffMapper.getStaffByAccount(account);
    }

    @Override
    public int addDepartments(Set<StaffDepartmentRel> staffDepartmentRelSet) {
        return staffMapper.addDepartments(staffDepartmentRelSet);
    }

    @Override
    public int deleteDepartments(Set<StaffDepartmentRel> staffDepartmentRelSet) {
        return staffMapper.deleteDepartments(staffDepartmentRelSet);
    }

    @Override
    public int addRoles(Set<StaffRoleRel> staffRoleRelSet) {
        return staffMapper.addRoles(staffRoleRelSet);
    }

    @Override
    public int deleteRoles(Set<StaffRoleRel> staffRoleRelSet) {
        return staffMapper.deleteRoles(staffRoleRelSet);
    }
}
