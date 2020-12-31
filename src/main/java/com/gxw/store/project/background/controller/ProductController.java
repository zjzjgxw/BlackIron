package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.product.dto.AddProductAttributes;
import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.dto.ProductSearchParams;
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

    @NeedToken
    @PostMapping("/detail")
    public ResponseResult create(@Valid @RequestBody ProductDetail detail) {
        detail.setBusinessId(SessionUtils.getBusinessId());
        Long id = productService.createProductDetail(detail);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
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
    @NeedToken
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
     *
     * @param addProductAttributes
     * @return
     */
    @NeedToken
    @PostMapping("/detail/attributes")
    public ResponseResult addAttributes(@Valid @RequestBody AddProductAttributes addProductAttributes) {
        boolean success = productService.addAttributes(addProductAttributes.getDetailId(), addProductAttributes.getAttributes());
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @PostMapping("/detail/images/{id}")
    public ResponseResult createImages(@PathVariable(name = "id") Long detailId, @RequestBody ProductImages images) {
        Long id = productService.createProductImages(detailId, images);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PutMapping("/detail/images/{id}")
    public ResponseResult updateImages(@PathVariable(name = "id") Long detailId, @RequestBody ProductImages images) {
        productService.updateProductImages(detailId, images);
        return ResponseResult.success();
    }

    @NeedToken
    @GetMapping("/detail/{id}")
    public ResponseResult getDetail(@PathVariable Long id) {
        ProductDetail detail = productService.getDetailById(id);
        HashMap<String, ProductDetail> res = new HashMap<>();
        res.put("detail", detail);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping()
    public ResponseResult selectProduct(@RequestParam(required = false) Long categoryId,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) Long statusType,
                                        @RequestParam(required = false) Long mode) {
        startPage();
        ProductSearchParams params = new ProductSearchParams();
        params.setBusinessId(SessionUtils.getBusinessId());
        params.setCategoryId(categoryId);
        params.setName(name);
        params.setStatusType(statusType);
        params.setMode(mode);
        List<ProductDetail> details = productService.selectProducts(params);
        return ResponseResult.success(getDataTable(details));
    }

    @NeedToken
    @DeleteMapping("/detail/{id}")
    public ResponseResult deleteDetail(@PathVariable Long id) {
        if (productService.deleteDetail(id, SessionUtils.getBusinessId())) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 添加推荐商品
     *
     * @param recommends
     * @return
     */
    @NeedToken
    @PostMapping("/recommend")
    public ResponseResult addRecommend(@RequestBody List<ProductRecommend> recommends) {
        Long businessId = SessionUtils.getBusinessId();
        for (ProductRecommend item : recommends) {
            item.setBusinessId(businessId);
        }
        productService.addRecommend(recommends);
        return ResponseResult.success();
    }

    @NeedToken
    @DeleteMapping("/recommend")
    public ResponseResult deleteRecommend(@RequestBody List<Long> productIds) {
        if (productService.deleteRecommend(SessionUtils.getBusinessId(), productIds)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @PutMapping("/recommend")
    public ResponseResult updateRecommend(@RequestBody ProductRecommend recommend) {
        recommend.setBusinessId(SessionUtils.getBusinessId());
        if (productService.updateRecommend(recommend)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @GetMapping("/recommend")
    public ResponseResult getRecommend() {
        startPage();
        List<ProductDetail> details = productService.getRecommendProducts(SessionUtils.getBusinessId());
        return ResponseResult.success(getDataTable(details));
    }
}
