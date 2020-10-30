package com.gxw.store.project.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {
    private Long id;

    @NotNull
    private Long businessId;

    @NotBlank(message = "account 不能为空")
    @Length(min = 5,max = 50,message = "账号长度需在5-50个字符之间")
    private String account;
    private String password;
    @NotBlank(message = "name 不能为空")
    @Length(min = 1,max = 20,message = "昵称在1-20个字符")
    private String name;
    @Email(message = "email 格式错误")
    private String email;
    @NotBlank(message = "请填写电话信息")
    private String tel;
    private int status;

    //微信openId
    private String openId;

    //微信unionId
    private String unionId;

    /**
     * 头像图
     */
    @NotBlank(message = "profileUrl 不能为空")
    private String profileUrl;

    /**
     * 关注者数量
     */
    private int idolNum;

    /**
     * 粉丝数量
     */
    private int fansNum;


    private Long consumePrice; //总消费金额

    private VipInfo vip; //vip信息

    @JsonIgnore
    private int deleteFlag;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public int getIdolNum() {
        return idolNum;
    }

    public void setIdolNum(int idolNum) {
        this.idolNum = idolNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Long getConsumePrice() {
        return consumePrice;
    }

    public void setConsumePrice(Long consumePrice) {
        this.consumePrice = consumePrice;
    }

    public VipInfo getVip() {
        return vip;
    }

    public void setVip(VipInfo vip) {
        this.vip = vip;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}
