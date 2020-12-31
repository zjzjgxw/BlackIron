package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.user.entity.business.Banner;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("app/banners")
public class AppBannerController extends BaseController {

    @Resource
    private BusinessService businessService;

    @GetMapping()
    public ResponseResult getBanners(@RequestParam Long businessId) {
        List<Banner> banners = businessService.getBanners(businessId);
        HashMap<String, List<Banner>> res = new HashMap<>();
        res.put("banners", banners);
        return ResponseResult.success(res);
    }



}
