package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.entity.*;
import com.gxw.store.project.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
    @Resource
    private ProductService productService;

    @PostMapping("/detail")
    public ResponseResult create(@Valid @RequestBody ProductDetail detail) {
        Long id = productService.createProductDetail(detail);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @PostMapping("/detail/images/{id}")
    public ResponseResult createImages(@PathVariable(name = "id") Long detailId, @RequestBody ProductImages images){
        Long id = productService.createProductImages(detailId,images);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }


    @GetMapping("/detail/{id}")
    public ResponseResult getDetail(@PathVariable Long id){
        ProductDetail detail = productService.getDetailById(id);
        HashMap<String, ProductDetail> res = new HashMap<>();
        res.put("detail", detail);
        return ResponseResult.success(res);
    }
}
