package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.user.entity.business.Advertisement;
import com.gxw.store.project.user.entity.business.Banner;
import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/")
    public ResponseResult create(@Valid @RequestBody Business business) {
        Long id = businessService.create(business);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping("/{id}")
    public ResponseResult getBusiness(@PathVariable Long id) {
        Business business = businessService.getBusiness(id);
        HashMap<String, Business> res = new HashMap<>();
        res.put("business", business);
        return ResponseResult.success(res);
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
        if(businessService.updateBanner(banner)){
            return ResponseResult.success();
        };
        return ResponseResult.error();
    }

    @DeleteMapping("/banners/{id}")
    public ResponseResult deleteBanner(@PathVariable Long id) {
        if(businessService.deleteBanner(id,SessionUtils.getBusinessId())){
            return ResponseResult.success();
        };
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
        if(businessService.updateAdvertisement(banner)){
            return ResponseResult.success();
        };
        return ResponseResult.error();
    }

    @DeleteMapping("/advertisements/{id}")
    public ResponseResult deleteAdvertisement(@PathVariable Long id) {
        if(businessService.deleteAdvertisement(id,SessionUtils.getBusinessId())){
            return ResponseResult.success();
        };
        return ResponseResult.error();
    }
}
