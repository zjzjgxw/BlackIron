package com.gxw.store.project.user.controller;


import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/")
    public ResponseResult create(@Valid @RequestBody Business business){
        Long id = businessService.create(business);
        HashMap<String,Long> res= new HashMap<>();
        res.put("id",id);
        return ResponseResult.success(res);
    }

    @GetMapping("/{id}")
    public ResponseResult getBusiness(@PathVariable Long id){
        Business business = businessService.getBusiness(id);
        HashMap<String, Business> res= new HashMap<>();
        res.put("business",business);
        return ResponseResult.success(res);
    }
}
