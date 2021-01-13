package com.gxw.store.project.order.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 订单统计数据
 */
public class OrderStatTime {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private int paidNum; //成交订单数

    private int paidPrice; //成交额


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getPaidNum() {
        return paidNum;
    }

    public void setPaidNum(int paidNum) {
        this.paidNum = paidNum;
    }

    public int getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(int paidPrice) {
        this.paidPrice = paidPrice;
    }
}
