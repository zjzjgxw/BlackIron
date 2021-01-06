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

    @Override
    public VipInfo getCurrentVipInfo(Long consumePrice, List<VipInfo> vipInfos) {
        VipInfo current = vipInfos.get(0);
        for (VipInfo vipInfo : vipInfos) {
            if (consumePrice >= vipInfo.getConsumePrice()) {
                current = vipInfo;
            } else {
                break;
            }
        }
        return current;
    }

    @Override
    public boolean update(VipInfo vipInfo) {
        int row = vipMapper.update(vipInfo);
        return row != 0;
    }

    @Override
    public boolean delete(Long id, Long businessId) {
        int row = vipMapper.delete(id,businessId);
        return row !=0;
    }
}
