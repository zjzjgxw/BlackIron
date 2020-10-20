package com.gxw.store.project.user.dto;


public class PermissionSearchParams {
    private Long id;
    private int type;
    private String name;
    private Long groupId;

    public PermissionSearchParams() {
    }

    public PermissionSearchParams(Long id, int type, String name, Long groupId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
