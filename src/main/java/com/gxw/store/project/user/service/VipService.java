package com.gxw.store.project.user.service;


import com.gxw.store.project.user.entity.VipInfo;

import java.util.List;


public interface VipService {
    Long create(VipInfo vipInfo);

    List<VipInfo> getVips(Long businessId);

    VipInfo getVipInfo(Long id, Long businessId);

    VipInfo getCurrentVipInfo(Long consumePrice, List<VipInfo> vipInfos);

    boolean update(VipInfo vipInfo);

    boolean delete(Long id, Long businessId);
}
