package com.gxw.store.project.common.service;



import com.gxw.store.project.order.entity.Order;

import java.io.IOException;
import java.util.HashMap;

public interface WeixinPayService {

    HashMap<String, Object> getPayInfo(Order order, String openId) throws IOException;



}
