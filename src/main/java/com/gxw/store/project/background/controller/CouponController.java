package com.gxw.store.project.background.controller;

import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.sale.dto.SendCouponParams;
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

    @NeedToken
    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Coupon coupon) {
        coupon.setStaffId(SessionUtils.getUserId());
        coupon.setBusinessId(SessionUtils.getBusinessId());
        Long id = couponService.create(coupon);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    /**
     * 发放优惠券
     *
     * @return
     */
    @NeedToken
    @PostMapping("/send")
    public ResponseResult sendCoupon(@RequestBody SendCouponParams params) {
        couponService.send(params.getId(),SessionUtils.getBusinessId(), params.getUserIds());
        return ResponseResult.success();
    }

    @NeedToken
    @GetMapping()
    public ResponseResult getCoupons(@RequestParam(required = false) String name) {
        startPage();
        List<Long> ids = couponService.getCouponIds(SessionUtils.getBusinessId(),name);
        List<Coupon> coupons = couponService.getCoupons(ids);
        return ResponseResult.success(getDataTable(coupons,ids));
    }

    @NeedToken
    @PutMapping()
    public ResponseResult update(@Valid @RequestBody Coupon coupon) {
        coupon.setStaffId(SessionUtils.getUserId());
        coupon.setBusinessId(SessionUtils.getBusinessId());
        couponService.update(coupon);
        return ResponseResult.success();
    }

    @NeedToken
    @DeleteMapping("/{id}")
    public ResponseResult deleteDiscount(@PathVariable Long id) {
        couponService.delete(id, SessionUtils.getBusinessId());
        return ResponseResult.success();
    }

}
