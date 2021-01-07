package com.gxw.store.project.sale.dto;


import javax.validation.constraints.NotNull;

public class SendCouponParams {
    @NotNull
    private  Long id;
    @NotNull
    private Long[] userIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }
}
