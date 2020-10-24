package com.gxw.store.project.background.controller;

import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.sale.entity.Discount;
import com.gxw.store.project.sale.service.DiscountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    @Resource
    private DiscountService discountService;

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Discount discount) {
        discount.setUserId(SessionUtils.getUserId());
        discount.setBusinessId(SessionUtils.getBusinessId());
        Long id = discountService.create(discount);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping()
    public ResponseResult getDiscounts() {
        List<Discount> discounts = discountService.getDiscounts(SessionUtils.getBusinessId());
        HashMap<String, List<Discount>> res = new HashMap<>();
        res.put("discounts", discounts);
        return ResponseResult.success(res);
    }

    @PutMapping()
    public ResponseResult updateDiscount(@Valid @RequestBody Discount discount) {
        discount.setUserId(SessionUtils.getUserId());
        discount.setBusinessId(SessionUtils.getBusinessId());
        discountService.update(discount);
        return ResponseResult.success();
    }


    @DeleteMapping("/{id}")
    public ResponseResult deleteDiscount(@PathVariable Long id)
    {
        discountService.delete(id,SessionUtils.getBusinessId());
        return ResponseResult.success();
    }

}
