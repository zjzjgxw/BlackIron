package com.gxw.store.project.background.controller;

import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.sale.entity.Discount;
import com.gxw.store.project.sale.service.DiscountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

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
    public ResponseResult getDiscounts()
    {
        return ResponseResult.success();
    }

}
