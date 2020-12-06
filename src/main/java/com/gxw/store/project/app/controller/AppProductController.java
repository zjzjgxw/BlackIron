package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.product.dto.AddProductAttributes;
import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.dto.ProductSearchParams;
import com.gxw.store.project.product.entity.Category;
import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.ProductRecommend;
import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.service.CategoryService;
import com.gxw.store.project.product.service.ProductService;
import com.gxw.store.project.product.service.StockService;
import com.gxw.store.project.sale.service.DiscountService;
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

    @Resource
    private StockService stockService;

    @Resource
    private DiscountService discountService;

    @GetMapping("/recommend")
    public ResponseResult getRecommend(@RequestParam Long businessId) {
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
        ProductSearchParams params = new ProductSearchParams();
        params.setBusinessId(businessId);
        params.setCategoryId(categoryId);
        List<ProductDetail> details = productService.selectProducts(params);
        return ResponseResult.success(getDataTable(details));
    }

    @GetMapping("/detail/{id}")
    public ResponseResult getDetail(@PathVariable Long id) {
        ProductDetail detail = productService.getDetailById(id);
        HashMap<String, ProductDetail> res = new HashMap<>();
        res.put("detail", detail);
        return ResponseResult.success(res);
    }

    @GetMapping("/stock")
    public ResponseResult getStockInfo(@RequestParam Long businessId, @RequestParam Long productId) {
        StockInfo info = stockService.getStockInfoByProductId(productId);
        Long discount = discountService.getDiscountOfProduct(businessId, productId);
        HashMap<String, Object> res = new HashMap<>();
        res.put("info", info);
        res.put("discount", discount);
        return ResponseResult.success(res);
    }

}
