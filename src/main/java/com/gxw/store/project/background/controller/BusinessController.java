package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.entity.City;
import com.gxw.store.project.common.entity.County;
import com.gxw.store.project.common.entity.Province;
import com.gxw.store.project.common.service.AreaService;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.user.entity.business.Advertisement;
import com.gxw.store.project.user.entity.business.Banner;
import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Resource
    private AreaService areaService;

    @PostMapping("/")
    public ResponseResult create(@Valid @RequestBody Business business) {
        Long id = businessService.create(business);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping()
    public ResponseResult getBusiness() {
        Business business = businessService.getBusiness(SessionUtils.getBusinessId());
        HashMap<String, Business> res = new HashMap<>();
        res.put("business", business);
        return ResponseResult.success(res);
    }

    @PutMapping()
    public ResponseResult updateBusiness(@Valid @RequestBody Business business){
        business.setId(SessionUtils.getBusinessId());
        if(businessService.updateBusiness(business)){
            return ResponseResult.success();
        }else{
            return ResponseResult.error();
        }
    }

    @PostMapping("/banners")
    public ResponseResult addBanner(@RequestBody Banner banner) {
        banner.setBusinessId(SessionUtils.getBusinessId());
        Long id = businessService.addBanner(banner);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping("/banners")
    public ResponseResult getBanners() {
        List<Banner> banners = businessService.getBanners(SessionUtils.getBusinessId());
        HashMap<String, List<Banner>> res = new HashMap<>();
        res.put("banners", banners);
        return ResponseResult.success(res);
    }

    @PutMapping("/banners")
    public ResponseResult updateBanner(@RequestBody Banner banner) {
        banner.setBusinessId(SessionUtils.getBusinessId());
        if (businessService.updateBanner(banner)) {
            return ResponseResult.success();
        }
        ;
        return ResponseResult.error();
    }

    @DeleteMapping("/banners/{id}")
    public ResponseResult deleteBanner(@PathVariable Long id) {
        if (businessService.deleteBanner(id, SessionUtils.getBusinessId())) {
            return ResponseResult.success();
        }
        ;
        return ResponseResult.error();
    }

    @PostMapping("/advertisements")
    public ResponseResult addAdvertisement(@RequestBody Advertisement advertisement) {
        advertisement.setBusinessId(SessionUtils.getBusinessId());
        Long id = businessService.addAdvertisement(advertisement);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping("/advertisements")
    public ResponseResult getAdvertisements() {
        List<Advertisement> advertisements = businessService.getAdvertisements(SessionUtils.getBusinessId());
        HashMap<String, List<Advertisement>> res = new HashMap<>();
        res.put("advertisements", advertisements);
        return ResponseResult.success(res);
    }

    @PutMapping("/advertisements")
    public ResponseResult updateAdvertisement(@RequestBody Advertisement banner) {
        banner.setBusinessId(SessionUtils.getBusinessId());
        if (businessService.updateAdvertisement(banner)) {
            return ResponseResult.success();
        }
        ;
        return ResponseResult.error();
    }

    @DeleteMapping("/advertisements/{id}")
    public ResponseResult deleteAdvertisement(@PathVariable Long id) {
        if (businessService.deleteAdvertisement(id, SessionUtils.getBusinessId())) {
            return ResponseResult.success();
        }
        ;
        return ResponseResult.error();
    }


    @GetMapping("/provinces")
    public ResponseResult getProvinces() {
        List<Province> provinces = areaService.getProvinces();
        HashMap<String, List<Province>> res = new HashMap<>();
        res.put("provinces", provinces);
        return ResponseResult.success(res);
    }

    @GetMapping("/cities")
    public ResponseResult getCities(@RequestParam Long provinceId) {
        List<City> cities = areaService.getCities(provinceId);
        HashMap<String, List<City>> res = new HashMap<>();
        res.put("cities", cities);
        return ResponseResult.success(res);
    }

    @GetMapping("/counties")
    public ResponseResult getCounties(@RequestParam Long cityId) {
        List<County> counties = areaService.getCounties(cityId);
        HashMap<String, List<County>> res = new HashMap<>();
        res.put("counties", counties);
        return ResponseResult.success(res);
    }
}
