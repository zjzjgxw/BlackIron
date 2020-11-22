package com.gxw.store.project.app.controller;

import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.sale.entity.Coupon;
import com.gxw.store.project.sale.service.CouponService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("app/coupons")
public class AppCouponController extends BaseController {

    @Resource
    private CouponService couponService;


    /**
     * 领取优惠券
     *
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    public ResponseResult receiveCoupon(@PathVariable Long id) {
        Long userId = SessionUtils.getUserId();
        couponService.send(id, new Long[]{userId});
        return ResponseResult.success();
    }

    /**
     * 获取优惠券列表
     * @return
     */
    @GetMapping
    public ResponseResult getCoupons(@RequestParam(required = false) List<Long> productIds) {
        Long userId = SessionUtils.getUserId();
        List<Coupon> coupons = couponService.getCouponsOfUser(userId,productIds);
        HashMap<String,  List<Coupon>> res = new HashMap<>();
        res.put("coupons", coupons);
        return ResponseResult.success(res);
    }

}
