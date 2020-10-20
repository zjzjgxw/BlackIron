package com.gxw.store.project.user.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class Permission {
    private Long id;
    @Min(value = 1,message = "最小值为1")
    @Max(value = 2,message = "最大值为2")
    private int type; //权限类型，1为商户，2为系统后台
    @NotBlank( message = "权限名称不能为空")
    @Length(min = 2,max = 20,message = "权限名称长度在2-20")
    private String name;
    @NotBlank( message = "权限路径不能为空")
    private String path;
    @NotBlank(message = "调用方式不能为空")
    @Pattern(regexp = "(get|post|delete|head|put|patch|connect|options|trace)", message = "http请求方式错误")
    private String method;
    private String info;
    @NotNull(message = "权限组id不能为空")
    private Long groupId = 0L;

    private String groupName;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
