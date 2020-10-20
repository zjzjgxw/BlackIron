package com.gxw.store.project.user.dto;


import javax.validation.constraints.NotNull;
import java.util.Objects;

public class StaffRoleRel {
    @NotNull
    private  Long staffId;
    @NotNull
    private Long roleId;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffRoleRel that = (StaffRoleRel) o;
        return Objects.equals(staffId, that.staffId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, roleId);
    }
}
