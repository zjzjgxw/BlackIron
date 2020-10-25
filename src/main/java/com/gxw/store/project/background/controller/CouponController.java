package com.gxw.store.project.background.controller;

import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.sale.entity.Coupon;
import com.gxw.store.project.sale.entity.Discount;
import com.gxw.store.project.sale.service.CouponService;
import com.gxw.store.project.sale.service.DiscountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController extends BaseController {

    @Resource
    private CouponService couponService;

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Coupon coupon) {
        coupon.setStaffId(SessionUtils.getUserId());
        coupon.setBusinessId(SessionUtils.getBusinessId());
        Long id = couponService.create(coupon);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping()
    public ResponseResult getCoupons() {
        startPage();
        List<Coupon> coupons = couponService.getCoupons(SessionUtils.getBusinessId());
        return ResponseResult.success(getDataTable(coupons));
    }


    @PutMapping()
    public ResponseResult update(@Valid @RequestBody Coupon coupon) {
        coupon.setStaffId(SessionUtils.getUserId());
        coupon.setBusinessId(SessionUtils.getBusinessId());
        couponService.update(coupon);
        return ResponseResult.success();
    }


    @DeleteMapping("/{id}")
    public ResponseResult deleteDiscount(@PathVariable Long id) {
        couponService.delete(id, SessionUtils.getBusinessId());
        return ResponseResult.success();
    }

}
