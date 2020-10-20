package com.gxw.store.project.user.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RolePermissionRel {
    @NotNull
    private  Long roleId;
    @NotNull
    @Size(min = 1,message = "权限数组不能为空")
    private Long[] permissions;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long[] getPermissions() {
        return permissions;
    }

    public void setPermissions(Long[] permissions) {
        this.permissions = permissions;
    }
}
