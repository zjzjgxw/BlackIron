package com.gxw.store.project.order.dto;


public class WeChatPayParams {
    private String appid; // 小程序/公众号id
    private String mchid; // 微信商户id
    private String description; //商品描述
    private String out_trade_no; //订单唯一code
    private String attach; //附加信息
    private String notify_url; //通知url
    private Amount amount;
    private Payer payer;


    public WeChatPayParams(String appid, String mchid, String description, String out_trade_no, String attach, Long totalPrice, String currency, String openId) {
        this.appid = appid;
        this.mchid = mchid;
        this.description = description;
        this.out_trade_no = out_trade_no;
        this.attach = attach;
        this.amount = new Amount(totalPrice, currency);
        this.payer = new Payer(openId);
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}


class Amount {
    private Long total;
    private String currency;

    public Amount(Long total, String currency) {
        this.total = total;
        this.currency = currency;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

class Payer {
    private String openid;

    public Payer(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}

