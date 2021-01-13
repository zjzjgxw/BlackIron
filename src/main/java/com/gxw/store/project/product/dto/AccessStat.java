package com.gxw.store.project.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 访问数据
 */
public class AccessStat {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private Long pv; //页面访问量

    private Long uv; //访问人数

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

    public Long getUv() {
        return uv;
    }

    public void setUv(Long uv) {
        this.uv = uv;
    }
}
