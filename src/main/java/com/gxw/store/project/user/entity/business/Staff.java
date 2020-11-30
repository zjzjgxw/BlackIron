package com.gxw.store.project.user.entity.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.gxw.store.project.common.utils.view.ViewUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Staff {

    @JsonView(ViewUtils.Simple.class)
    private Long id;
    private Long businessId;
    @NotBlank(message = "账号不能为空")
    @Length(min = 5, max = 25, message = "账号长度在5-25")
    private String account;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 25, message = "密码长度在6-25")
    private String password;
    @NotBlank(message = "昵称不能为空")
    @JsonView(ViewUtils.Simple.class)
    private String name;
    @NotNull
    private boolean admin;
    @Email(message = "邮箱格式错误")
    private String email;

    private String logoImg;
    private StaffStatus status;

    @JsonIgnore
    private int deleteFlag;

    private List<BusinessDepartment> departments;

    private List<BusinessRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long business_id) {
        this.businessId = business_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public void setStatus(StaffStatus status) {
        this.status = status;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<BusinessDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(List<BusinessDepartment> departments) {
        this.departments = departments;
    }

    public List<BusinessRole> getRoles() {
        return roles;
    }

    public void setRoles(List<BusinessRole> roles) {
        this.roles = roles;
    }
}
