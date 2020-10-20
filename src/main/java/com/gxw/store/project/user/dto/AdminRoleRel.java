package com.gxw.store.project.user.dto;


import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AdminRoleRel {
    @NotNull
    private  Long adminId;
    @NotNull
    private Long roleId;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
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
        AdminRoleRel that = (AdminRoleRel) o;
        return Objects.equals(adminId, that.adminId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, roleId);
    }
}
