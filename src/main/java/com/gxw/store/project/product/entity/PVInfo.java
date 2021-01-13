package com.gxw.store.project.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PVInfo {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private Long pv; //页面访问量

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }
}
