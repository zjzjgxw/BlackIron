package com.gxw.store.project.user.VO;

import com.gxw.store.project.user.entity.admin.Admin;
import com.gxw.store.project.user.entity.admin.AdminRole;
import com.gxw.store.project.user.entity.admin.AdminStatus;

import java.util.List;

public class AdminVO {

    private Long id;
    private String account;
    private String name;
    private boolean adminFlag;
    private String email;
    private String logoImg;
    private AdminStatus status;

    private List<AdminRole> roles;

    public AdminVO(Admin admin) {
        this.id = admin.getId();
        this.account = admin.getAccount();
        this.name = admin.getName();
        this.adminFlag = admin.getAdminFlag();
        this.email = admin.getEmail();
        this.logoImg = admin.getLogoImg();
        this.status = admin.getStatus();
        this.roles = admin.getRoles();
    }

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

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }
}
