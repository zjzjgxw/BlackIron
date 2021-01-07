package com.gxw.store.project.user.dto;

import java.util.Date;
import java.util.List;


public class UserSearchParams {
    private Long id;
    private Long businessId;
    private String account;
    private String email;
    private String tel;
    private String name;
    private Date birthday;
    private Long vipId;
    private List<Integer> status;

    public UserSearchParams() {
    }

    public UserSearchParams(Long id, Long businessId, String account, String tel, String name, String email, Date birthday, Long vipId, List<Integer> status) {
        this.id = id;
        this.businessId = businessId;
        this.account = account;
        this.tel = tel;
        this.name = name;
        this.email = email;
        this.status = status;
        this.birthday = birthday;
        this.vipId = vipId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getVipId() {
        return vipId;
    }

    public void setVipId(Long vipId) {
        this.vipId = vipId;
    }
}
