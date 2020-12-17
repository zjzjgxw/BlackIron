package com.gxw.store.project.app.controller;

import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.user.entity.business.Advertisement;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("app/home")
public class AppHomeController extends BaseController {

    @Resource
    private BusinessService businessService;

    @GetMapping("/advertisements")
    public ResponseResult getAdvertisements(@RequestParam Long businessId) {
        List<Advertisement> advertisements = businessService.getAdvertisements(businessId);
        HashMap<String, List<Advertisement>> res = new HashMap<>();
        res.put("advertisements", advertisements);
        return ResponseResult.success(res);
    }


}
