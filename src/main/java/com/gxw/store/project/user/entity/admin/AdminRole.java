package com.gxw.store.project.user.entity.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.gxw.store.project.common.utils.view.ViewUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 管理员角色
 */
public class AdminRole {

    @JsonView(ViewUtils.Simple.class)
    private Long id;

    @NotBlank(message = "角色名称不能为空")
    @Length(min = 2,max = 20,message = "角色名称长度在2-20个字")
    @JsonView(ViewUtils.Simple.class)
    private String name;
    @JsonIgnore
    private int deleteFlag;

    @JsonView(ViewUtils.Simple.class)
    private List<Admin> admins;

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

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }
}
