package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.order.entity.Express;
import com.gxw.store.project.order.service.ExpressService;
import com.gxw.store.project.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/expresses")
public class ExpressController {

    @Resource
    private ExpressService expressService;

    /**
     * 获取快递列表
     *
     * @return
     */
    @GetMapping
    public ResponseResult select() {
        List<Express> expressList = expressService.select();
        HashMap<String, List<Express>> res = new HashMap<>();
        res.put("express", expressList);
        return ResponseResult.success(res);
    }
}
