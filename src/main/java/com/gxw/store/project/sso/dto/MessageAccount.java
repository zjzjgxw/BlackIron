package com.gxw.store.project.sso.dto;


import javax.validation.constraints.NotBlank;

public class MessageAccount {

    @NotBlank
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
