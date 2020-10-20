package com.gxw.store.project.user.entity.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.gxw.store.project.common.utils.view.ViewUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Admin {

    @JsonView(ViewUtils.Simple.class)
    private Long id;
    @NotBlank(message = "账号不能为空")
    @Length(min = 5, max = 25, message = "账号长度在5-25")
    private String account;
    private String password;
    @NotBlank(message = "昵称不能为空")
    @JsonView(ViewUtils.Simple.class)
    private String name;
    @NotNull
    private boolean adminFlag;
    @Email(message = "邮箱格式错误")
    private String email;

    private String logoImg;

    private AdminStatus status;

    @JsonIgnore
    private int deleteFlag;

    private List<AdminRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(boolean adminFlag) {
        this.adminFlag = adminFlag;
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

    public AdminStatus getStatus() {
        return status;
    }

    public void setStatus(AdminStatus status) {
        this.status = status;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }

    public boolean isUsable()
    {
        return this.status == AdminStatus.USABLE;
    }
}
