package com.gxw.store.project.user.mapper;


import com.gxw.store.project.user.entity.VipInfo;

import java.util.List;


public interface VipMapper {
     void create(VipInfo vipInfo);

     List<VipInfo> getVips(Long businessId);

     VipInfo getVipInfo(Long id,Long businessId);

}
