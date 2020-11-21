package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.entity.business.Banner;
import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.service.BusinessService;
import com.gxw.store.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
