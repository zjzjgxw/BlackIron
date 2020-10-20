package com.gxw.store.project.user.entity.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.gxw.store.project.common.utils.view.ViewUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商户角色
 */
public class BusinessRole {

    @JsonView(ViewUtils.Simple.class)
    private Long id;
    @NotNull(message = "商户id不能为空")
    @JsonView(ViewUtils.Simple.class)
    private Long businessId;
    @NotBlank(message = "角色名称不能为空")
    @Length(min = 2,max = 20,message = "角色名称长度在2-20个字")
    @JsonView(ViewUtils.Simple.class)
    private String name;
    @JsonIgnore
    private int delete_flag;

    @JsonView(ViewUtils.Simple.class)
    private List<Staff> staffs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(int delete_flag) {
        this.delete_flag = delete_flag;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }
}
