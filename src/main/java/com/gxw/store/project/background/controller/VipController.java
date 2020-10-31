package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.user.entity.VipInfo;
import com.gxw.store.project.user.service.VipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/vips")
public class VipController extends BaseController {

    @Resource
    private VipService vipService;

    @PostMapping
    public ResponseResult create(@Valid @RequestBody VipInfo info) {
        info.setBusinessId(SessionUtils.getBusinessId());
        Long id = vipService.create(info);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }


    @GetMapping
    public ResponseResult getVips(){
        List<VipInfo> vipInfos = vipService.getVips(SessionUtils.getBusinessId());
        HashMap<String, List<VipInfo>> res = new HashMap<>();
        res.put("vips", vipInfos);
        return ResponseResult.success(res);
    }

    @GetMapping("/{id}")
    public ResponseResult getVipDetail(@PathVariable Long id){
        VipInfo vipInfo = vipService.getVipInfo(id,SessionUtils.getBusinessId());
        HashMap<String, VipInfo> res = new HashMap<>();
        res.put("vip", vipInfo);
        return ResponseResult.success(res);
    }
}