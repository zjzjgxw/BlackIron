package com.gxw.store.project.common.mapper;

import com.gxw.store.project.common.entity.City;
import com.gxw.store.project.common.entity.County;
import com.gxw.store.project.common.entity.Province;

import java.util.List;

public interface AreaMapper {

    List<Province> getProvinces();

    List<City> getCities(Long provinceId);

    List<County> getCounties(Long cityId);
}
