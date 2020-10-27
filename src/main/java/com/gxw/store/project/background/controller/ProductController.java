package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.product.dto.AddProductAttributes;
import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.entity.*;
import com.gxw.store.project.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
    @Resource
    private ProductService productService;

    @PostMapping("/detail")
    public ResponseResult create(@Valid @RequestBody ProductDetail detail) {
        detail.setBusinessId(SessionUtils.getBusinessId());
        Long id = productService.createProductDetail(detail);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @PutMapping("/detail")
    public ResponseResult update(@Valid @RequestBody ProductDetail detail) {
        detail.setBusinessId(SessionUtils.getBusinessId());
        boolean success = productService.updateDetail(detail);
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除属性
     *
     * @param id
     * @return
     */
    @DeleteMapping("/detail/attributes/{id}")
    public ResponseResult removeAttribute(@PathVariable Long id, @RequestParam Long detailId) {
        boolean success = productService.removeAttribute(id, detailId);
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


    /**
     * 添加属性
     * @param addProductAttributes
     * @return
     */
    @PostMapping("/detail/attributes")
    public ResponseResult addAttributes(@Valid @RequestBody AddProductAttributes addProductAttributes){
        boolean success = productService.addAttributes(addProductAttributes.getDetailId(),addProductAttributes.getAttributes());
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @PostMapping("/detail/images/{id}")
    public ResponseResult createImages(@PathVariable(name = "id") Long detailId, @RequestBody ProductImages images) {
        Long id = productService.createProductImages(detailId, images);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @PutMapping("/detail/images/{id}")
    public ResponseResult updateImages(@PathVariable(name = "id") Long detailId, @RequestBody ProductImages images) {
        productService.updateProductImages(detailId, images);
        return ResponseResult.success();
    }


    @GetMapping("/detail/{id}")
    public ResponseResult getDetail(@PathVariable Long id) {
        ProductDetail detail = productService.getDetailById(id);
        HashMap<String, ProductDetail> res = new HashMap<>();
        res.put("detail", detail);
        return ResponseResult.success(res);
    }


    @GetMapping()
    public ResponseResult selectProduct(@RequestParam Long businessId, @RequestParam(required = false) Long categoryId) {
        startPage();
        List<ProductDetail> details = productService.selectProducts(businessId, categoryId);
        return ResponseResult.success(getDataTable(details));
    }
}
