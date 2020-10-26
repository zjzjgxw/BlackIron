package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("app/pay")
public class AppPayController {


    @GetMapping
    public ResponseResult getPayInfo() {
        return ResponseResult.success();
    }

    
}
