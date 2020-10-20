package com.gxw.store.project.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FollowRel {
    @NotNull(message = "userId 不能为空")
    private Long userId;
    @NotNull(message = "idolIds 不能为空")
    @Size(min = 1,message = "idolIds数组至少有一个数据")
    private Long[] idolIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getIdolIds() {
        return idolIds;
    }

    public void setIdolIds(Long[] idolIds) {
        this.idolIds = idolIds;
    }
}
