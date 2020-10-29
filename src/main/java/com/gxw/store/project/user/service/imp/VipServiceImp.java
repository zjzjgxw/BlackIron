package com.gxw.store.project.user.service.imp;

import com.gxw.store.project.user.entity.VipInfo;
import com.gxw.store.project.user.mapper.VipMapper;
import com.gxw.store.project.user.service.VipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class VipServiceImp implements VipService {
    @Resource
    private VipMapper vipMapper;

    @Override
    public Long create(VipInfo vipInfo) {
        vipMapper.create(vipInfo);
        return vipInfo.getId();
    }

    @Override
    public List<VipInfo> getVips(Long businessId) {
        return vipMapper.getVips(businessId);
    }

    @Override
    public VipInfo getVipInfo(Long id, Long businessId) {
        return vipMapper.getVipInfo(id, businessId);
    }
}
