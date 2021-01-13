package com.gxw.store.project.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UVInfo {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private Long uv; //页面访问量

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getUv() {
        return uv;
    }

    public void setUv(Long uv) {
        this.uv = uv;
    }
}
