package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.product.entity.*;
import com.gxw.store.project.product.service.CategoryService;
import com.gxw.store.project.product.service.ProductService;
import com.gxw.store.project.user.dto.NameParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Resource
    private CategoryService categoryService;


    @Resource
    private ProductService productService;

    @NeedToken
    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Category category) {
        category.setBusinessId(SessionUtils.getBusinessId());
        Long id = categoryService.create(category);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping()
    public ResponseResult getCategories() {
        List<Category> categories = categoryService.getCategories(SessionUtils.getBusinessId());
        HashMap<String, List<Category>> res = new HashMap<>();
        res.put("categories", categories);
        return ResponseResult.success(res);
    }

    @NeedToken
    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id, SessionUtils.getBusinessId())) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @PutMapping("/{id}")
    public ResponseResult updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        category.setBusinessId(SessionUtils.getBusinessId());
        if (categoryService.updateCategory(category)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    //------- 类目属性
    @NeedToken
    @PostMapping("/attributes")
    public ResponseResult createAttribute(@Valid @RequestBody CategoryAttribute categoryAttribute) {
        Long id = categoryService.createAttribute(categoryAttribute);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping("/attributes")
    public ResponseResult getAttributes(@RequestParam Long categoryId) {
        List<CategoryAttribute> attributes = categoryService.getAttributes(categoryId);
        HashMap<String, List<CategoryAttribute>> res = new HashMap<>();
        res.put("attributes", attributes);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PutMapping("/attributes/{id}")
    public ResponseResult updateAttributes(@PathVariable Long id, @RequestBody NameParam attributeName) {
        if (categoryService.updateAttribute(id, attributeName.getName())) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @DeleteMapping("/attributes/{id}")
    public ResponseResult deleteAttribute(@PathVariable Long id) {
        if (categoryService.deleteAttribute(id)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @PostMapping("/attributes/options")
    public ResponseResult createAttributeOptions(@Valid @RequestBody AttributeOption attributeOption) {
        Long id = categoryService.createAttributeOption(attributeOption);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping("/attributes/options")
    public ResponseResult getAttributeOptions(@RequestParam Long attributeId) {
        List<AttributeOption> attributeOptions = categoryService.selectAttributeOptions(attributeId);
        HashMap<String, List<AttributeOption>> res = new HashMap<>();
        res.put("options", attributeOptions);
        return ResponseResult.success(res);
    }

    @NeedToken
    @DeleteMapping("/attributes/options/{id}")
    public ResponseResult deleteAttributeOptions(@PathVariable Long id) {
        categoryService.deleteAttributeOption(id);
        return ResponseResult.success();
    }

    @NeedToken
    @PutMapping("/attributes/options")
    public ResponseResult updateAttributeOptions(@RequestBody AttributeOption attributeOption) {
        categoryService.updateAttributeOption(attributeOption);
        return ResponseResult.success();
    }


    // ---- 类目规格
    @NeedToken
    @PostMapping("/specifications")
    public ResponseResult createSpecification(@Valid @RequestBody CategorySpecification categorySpecification) {
        Long id = categoryService.createSpecification(categorySpecification);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PostMapping("/specifications/options")
    public ResponseResult createSpecificationOptions(@Valid @RequestBody SpecificationOption option) {
        Long id = categoryService.createSpecificationOption(option);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PutMapping("/specifications/options")
    public ResponseResult updateSpecificationOptions(@RequestBody SpecificationOption option) {
        categoryService.updateSpecificationOption(option);
        return ResponseResult.success();
    }

    @NeedToken
    @DeleteMapping("/specifications/options/{id}")
    public ResponseResult deleteSpecificationOptions(@PathVariable Long id) {
        categoryService.deleteSpecificationOption(id);
        return ResponseResult.success();
    }

    @NeedToken
    @GetMapping("/specifications")
    public ResponseResult getCategorySpecifications(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long productId) {
        if (productId != null) {
            ProductDetail detail = productService.getDetailById(productId);
            categoryId = detail.getCategoryId();
        }
        if (categoryId == null) {
            return ResponseResult.error();
        }
        List<CategorySpecification> specifications = categoryService.selectCategorySpecifications(categoryId);
        HashMap<String, List<CategorySpecification>> res = new HashMap<>();
        res.put("list", specifications);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PutMapping("/specifications/{id}")
    public ResponseResult updateSpecifications(@PathVariable Long id, @RequestBody NameParam param) {
        if (categoryService.updateSpecification(id, param.getName())) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @DeleteMapping("/specifications/{id}")
    public ResponseResult deleteSpecification(@PathVariable Long id) {
        if (categoryService.deleteSpecification(id)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

}
