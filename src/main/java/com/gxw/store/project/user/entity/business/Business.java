package com.gxw.store.project.user.entity.business;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商户信息
 */
public class Business {
    private Long id;
    @NotBlank(message = "商户名称不能为空")
    private String name;
    @NotBlank(message = "地址不能为空")
    private String address;
    @URL(message = "图片url格式错误")
    @NotNull
    private String logoImg;

    @NotNull(message = "公司规模必填")
    private BusinessScale scale;
    @NotBlank(message = "公司描述必填")
    private String describe;
    private BusinessStatus status;
    private int deleteFlag;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public BusinessScale getScale() {
        return scale;
    }

    public void setScale(BusinessScale size) {
        this.scale = size;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public BusinessStatus getStatus() {
        return status;
    }

    public void setStatus(BusinessStatus status) {
        this.status = status;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
