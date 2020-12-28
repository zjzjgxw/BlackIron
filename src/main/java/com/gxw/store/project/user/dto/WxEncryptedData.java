package com.gxw.store.project.user.dto;

import javax.validation.constraints.NotBlank;

public class WxEncryptedData {
    @NotBlank
    private String encryptedData;
    @NotBlank
    private String iv;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
