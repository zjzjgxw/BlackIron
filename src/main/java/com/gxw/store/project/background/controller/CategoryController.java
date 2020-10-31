package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.product.entity.*;
import com.gxw.store.project.product.service.CategoryService;
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

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Category category) {
        category.setBusinessId(SessionUtils.getBusinessId());
        Long id = categoryService.create(category);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping()
    public ResponseResult getCategories() {
        List<Category> categories = categoryService.getCategories(SessionUtils.getBusinessId());
        HashMap<String, List<Category>> res = new HashMap<>();
        res.put("categories", categories);
        return ResponseResult.success(res);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id, SessionUtils.getBusinessId())) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @PutMapping("/{id}")
    public ResponseResult updateCategory(@PathVariable Long id, @RequestParam String name) {
        if (categoryService.updateCategory(name, id, SessionUtils.getBusinessId())) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    //------- 类目属性

    @PostMapping("/attributes")
    public ResponseResult createAttribute(@Valid @RequestBody CategoryAttribute categoryAttribute) {
        Long id = categoryService.createAttribute(categoryAttribute);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }


    @PostMapping("/attributes/options")
    public ResponseResult createAttributeOptions(@Valid @RequestBody AttributeOption attributeOption) {
        Long id = categoryService.createAttributeOption(attributeOption);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }


    @GetMapping("/attributes/options")
    public ResponseResult getAttributeOptions(@RequestParam Long attributeId) {
        List<AttributeOption> attributeOptions = categoryService.selectAttributeOptions(attributeId);
        HashMap<String, List<AttributeOption>> res = new HashMap<>();
        res.put("admin", attributeOptions);
        return ResponseResult.success(res);
    }

    @DeleteMapping("/attributes/options/{id}")
    public ResponseResult deleteAttributeOptions(@PathVariable Long id) {
        categoryService.deleteAttributeOption(id);
        return ResponseResult.success();
    }

    @PutMapping("/attributes/options")
    public ResponseResult updateAttributeOptions(@RequestBody AttributeOption attributeOption) {
        categoryService.updateAttributeOption(attributeOption);
        return ResponseResult.success();
    }


    // ---- 类目规格
    @PostMapping("/specifications")
    public ResponseResult createSpecification(@Valid @RequestBody CategorySpecification categorySpecification) {
        Long id = categoryService.createSpecification(categorySpecification);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @PostMapping("/specifications/options")
    public ResponseResult createSpecificationOptions(@Valid @RequestBody SpecificationOption option) {
        Long id = categoryService.createSpecificationOption(option);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @PutMapping("/specifications/options")
    public ResponseResult updateSpecificationOptions(@RequestBody SpecificationOption option) {
        categoryService.updateSpecificationOption(option);
        return ResponseResult.success();
    }

    @DeleteMapping("/specifications/options/{id}")
    public ResponseResult deleteSpecificationOptions(@PathVariable Long id) {
        categoryService.deleteSpecificationOption(id);
        return ResponseResult.success();
    }

    @GetMapping("/specifications")
    public ResponseResult getCategorySpecifications(@RequestParam Long categoryId) {
        List<CategorySpecification> specifications = categoryService.selectCategorySpecifications(categoryId);
        HashMap<String, List<CategorySpecification>> res = new HashMap<>();
        res.put("list", specifications);
        return ResponseResult.success(res);
    }

}
