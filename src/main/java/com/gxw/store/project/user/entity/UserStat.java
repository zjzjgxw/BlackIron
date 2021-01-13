package com.gxw.store.project.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class UserStat {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private Long newNum; //新增用户数

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getNewNum() {
        return newNum;
    }

    public void setNewNum(Long newNum) {
        this.newNum = newNum;
    }
}
