package com.gxw.store.project.common.service.imp;

import com.gxw.store.project.common.entity.City;
import com.gxw.store.project.common.entity.County;
import com.gxw.store.project.common.entity.Province;
import com.gxw.store.project.common.mapper.AreaMapper;
import com.gxw.store.project.common.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaServiceImp implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Province> getProvinces() {
        return areaMapper.getProvinces();
    }

    @Override
    public List<City> getCities(Long provinceId) {
        return areaMapper.getCities(provinceId);
    }

    @Override
    public List<County> getCounties(Long cityId) {
        return areaMapper.getCounties(cityId);
    }
}
