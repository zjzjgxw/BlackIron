package com.gxw.store.project.background.controller;

import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
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
public class DiscountController extends BaseController {

    @Resource
    private DiscountService discountService;

    @NeedToken
    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Discount discount) {
        discount.setStaffId(SessionUtils.getUserId());
        discount.setBusinessId(SessionUtils.getBusinessId());
        Long id = discountService.create(discount);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping()
    public ResponseResult getDiscounts() {
        startPage();
        List<Discount> discounts = discountService.getDiscounts(SessionUtils.getBusinessId());
        return ResponseResult.success(getDataTable(discounts));
    }

    @NeedToken
    @PutMapping()
    public ResponseResult updateDiscount(@Valid @RequestBody Discount discount) {
        discount.setStaffId(SessionUtils.getUserId());
        discount.setBusinessId(SessionUtils.getBusinessId());
        discountService.update(discount);
        return ResponseResult.success();
    }

    @NeedToken
    @DeleteMapping("/{id}")
    public ResponseResult deleteDiscount(@PathVariable Long id)
    {
        discountService.delete(id,SessionUtils.getBusinessId());
        return ResponseResult.success();
    }

}
