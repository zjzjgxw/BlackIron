package com.gxw.store.project.user.dto;

import java.util.List;


public class AdminSearchParams {
    private Long id;
    private String account;
    private String email;
    private List<Integer> status;

    public AdminSearchParams() {
    }

    public AdminSearchParams(Long id, String account, String email, List<Integer> status) {
        this.id = id;
        this.account = account;
        this.email = email;
        this.status = status;
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
}
