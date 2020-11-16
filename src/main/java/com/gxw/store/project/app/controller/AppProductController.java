package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.product.dto.AddProductAttributes;
import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.entity.Category;
import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.ProductRecommend;
import com.gxw.store.project.product.service.CategoryService;
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

    @Resource
    private CategoryService categoryService;

    @GetMapping("/recommend")
    public ResponseResult getRecommend(@RequestParam Long businessId)
    {
        startPage();
        List<ProductDetail> details = productService.getRecommendProducts(businessId);
        return ResponseResult.success(getDataTable(details));
    }


    @GetMapping("/category")
    public ResponseResult getCategories(@RequestParam Long businessId) {
        List<Category> categories = categoryService.getCategories(businessId);
        HashMap<String, List<Category>> res = new HashMap<>();
        res.put("categories", categories);
        return ResponseResult.success(res);
    }

    @GetMapping()
    public ResponseResult selectProduct(@RequestParam Long businessId, @RequestParam(required = false) Long categoryId) {
        startPage();
        List<ProductDetail> details = productService.selectProducts(businessId, categoryId);
        return ResponseResult.success(getDataTable(details));
    }
}
