package com.gxw.store.project.user.mapper;

import com.gxw.store.project.user.dto.StaffDepartmentRel;
import com.gxw.store.project.user.dto.StaffRoleRel;
import com.gxw.store.project.user.dto.StaffUpdate;
import com.gxw.store.project.user.entity.business.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface StaffMapper {
    void create(Staff staff);

    Staff getStaff(Long id);

    int recordLogin(Long staffId, String ip);

    List<Staff> getStaffs(Long businessId);

    int update(StaffUpdate staffUpdate);

    int delete(Long id, Long businessId);

    Staff getStaffByAccount(String account);

    int addDepartments(@Param("set") Set<StaffDepartmentRel> staffDepartmentRelSet);

    int deleteDepartments(@Param("set") Set<StaffDepartmentRel> staffDepartmentRelSet);

    int addRoles(@Param("set") Set<StaffRoleRel> staffRoleRelSet);

    /**
     * 清除角色下的员工
     *
     * @param roleId
     * @return
     */
    int clearRoleRelations(Long roleId);

    int deleteRoles(@Param("set") Set<StaffRoleRel> staffRoleRelSet);
}
