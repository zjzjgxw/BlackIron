package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    public ResponseResult create() {
         Long userId = SessionUtils.getUserId();
        return ResponseResult.success(userId);
    }
}
