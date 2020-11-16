package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.product.dto.AddProductAttributes;
import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.ProductRecommend;
import com.gxw.store.project.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("app/products")
public class AppProductController extends BaseController {
    @Resource
    private ProductService productService;


    @GetMapping("/recommend")
    public ResponseResult getRecommend(@RequestParam Long businessId)
    {
        startPage();
        List<ProductDetail> details = productService.getRecommendProducts(businessId);
        return ResponseResult.success(getDataTable(details));
    }
}
