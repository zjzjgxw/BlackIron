package com.gxw.store.project.order.entity;


/**
 * 订单统计数据
 */
public class OrderStat {

    private int TotalNum; //订单总数

    private int unPaidNum; //未付款订单

    private int waitSendNum; //待发货订单

    private int refundNum; //申请退款订单

    public int getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(int totalNum) {
        TotalNum = totalNum;
    }

    public int getUnPaidNum() {
        return unPaidNum;
    }

    public void setUnPaidNum(int unPaidNum) {
        this.unPaidNum = unPaidNum;
    }

    public int getWaitSendNum() {
        return waitSendNum;
    }

    public void setWaitSendNum(int waitSendNum) {
        this.waitSendNum = waitSendNum;
    }

    public int getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(int refundNum) {
        this.refundNum = refundNum;
    }
}
