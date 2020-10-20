package com.gxw.store.project.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gxw.store.project.user.VO.PermissionSampleVO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class PermissionGroup {
    private Long id;
    @NotBlank( message = "权限组名称不能为空")
    @Length(min = 2,max = 20,message = "权限组名称长度在2-20")
    private String name;
    @Min(value = 1,message = "最小值为1")
    @Max(value = 2,message = "最大值为2")
    private int type;
    @JsonIgnore
    private int deleteFlag;

    private List<PermissionSampleVO> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<PermissionSampleVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionSampleVO> permissions) {
        this.permissions = permissions;
    }
}
