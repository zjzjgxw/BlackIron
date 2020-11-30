package com.gxw.store.project.user.dto;


import java.util.List;

public class GroupPermissionRel {
    private  Long groupId;
    private List<Long> permissions;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Long> permissions) {
        this.permissions = permissions;
    }
}
