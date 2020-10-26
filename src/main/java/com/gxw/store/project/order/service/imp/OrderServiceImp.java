package com.gxw.store.project.order.service.imp;

import com.alibaba.fastjson.JSON;
import com.gxw.store.project.common.utils.exception.MissSpecificationException;
import com.gxw.store.project.common.utils.exception.NotExistException;
import com.gxw.store.project.common.utils.exception.UnEnoughStockException;
import com.gxw.store.project.common.utils.exception.UnableServiceException;
import com.gxw.store.project.order.dto.OrderSearchParam;
import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.entity.OrderItem;
import com.gxw.store.project.order.entity.OrderStatus;
import com.gxw.store.project.order.mapper.OrderMapper;
import com.gxw.store.project.order.service.OrderService;
import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.entity.StockSpecification;
import com.gxw.store.project.product.service.ProductService;
import com.gxw.store.project.product.service.StockService;
import com.gxw.store.project.sale.entity.Coupon;
import com.gxw.store.project.sale.entity.Mode;
import com.gxw.store.project.sale.service.CouponService;
import com.gxw.store.project.sale.service.DiscountService;
import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.service.BusinessService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp implements OrderService {

    @Resource
    private OrderService proxySelf;

    @Resource
    private ProductService productService;

    @Resource
    private StockService stockService;

    @Resource
    private BusinessService businessService;

    @Resource
    private DiscountService discountService;

    @Resource
    private CouponService couponService;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public Long create(Order order) {
        //查找商户信息
        Business business = businessService.getBusiness(order.getBusinessId());
        order.setBusinessName(business.getName());
        order.setCode(RandomStringUtils.randomAlphanumeric(6) + new Date().getTime() / 1000);
        order.setStatus(OrderStatus.UNPAID);

        List<OrderItem> items = order.getItems();
        Long totalPrice = 0L;
        Long actualPrice = 0L;
        LinkedList<Long> productIds = new LinkedList<>();
        for (OrderItem item : items) {
            //获取产品信息
            ProductDetail detail = productService.getDetailById(item.getProductId());
            productIds.add(item.getProductId());
            item.setName(detail.getName());
            item.setCoverUrl(detail.getCoverUrl());
            item.setProductSnap(JSON.toJSONString(detail));
            item.setStockType(detail.getStockType());
            //获取库存信息
            StockInfo stockInfo = stockService.getStockInfoByProductId(item.getProductId());
            if (item.getSpecificationId() == 0 && stockInfo.getSpecifications().size() != 0) {
                throw new MissSpecificationException(); //缺少规格信息
            }
            if (item.getSpecificationId() == 0 && stockInfo.getSpecifications().size() == 0) { //不存在规格的情况
                if (item.getNum() > stockInfo.getLastNum()) {  //商品没有规格时，缺少库存
                    throw new UnEnoughStockException();
                }
                item.setOriginalPrice(stockInfo.getPrice() * item.getNum());

            }
            if (item.getSpecificationId() != 0) { //存在规格的情况
                //查找到对应的规格
                for (StockSpecification specification : stockInfo.getSpecifications()) {
                    if (item.getSpecificationId().equals(specification.getId())) {
                        if (item.getNum() > specification.getDetail().getLastNum()) {
                            throw new UnEnoughStockException();
                        }
                        item.setFirstSpecificationName(specification.getFirstName());
                        item.setFirstSpecificationValue(specification.getFirstValue());
                        item.setSecondSpecificationName(specification.getSecondName());
                        item.setSecondSpecificationValue(specification.getSecondValue());
                        item.setOriginalPrice(specification.getDetail().getPrice() * item.getNum()); //设置原价
                    }
                }
            }
            //根据促销活动那个获得实际价格
            item.setPrice(getDiscountPrice(detail, item.getOriginalPrice()));
            totalPrice += item.getOriginalPrice();
            actualPrice += item.getPrice();
        }

        order.setOriginalPrice(totalPrice);
        //TODO 后续添加快递费
        order.setExpressPrice(500L);

        Coupon coupon = getCoupon(order.getUserId(), order.getCouponId());
        Long couponPrice = 0L;
        if (coupon != null) {
            couponPrice = getCouponPrice(order.getBusinessId(), productIds.toArray(new Long[0]), actualPrice, coupon);//获取优惠券扣减价格
        }
        //订单最终价为实际价格 - 优惠券价格 + 邮费
        order.setPrice(actualPrice - couponPrice + order.getExpressPrice());

        return proxySelf.doCreate(order);
    }

    /**
     * 获取优惠价格
     *
     * @param businessId
     * @param productIds  包含的商品id
     * @param actualPrice 订单总价
     * @param coupon
     * @return
     */
    private Long getCouponPrice(Long businessId, Long[] productIds, Long actualPrice, Coupon coupon) {
        if (!coupon.getBusinessId().equals(businessId)) {
            throw new UnableServiceException("无法使用该优惠券");
        }
        if (coupon.getMode() == Mode.PRODUCT) {
            boolean isContains = false;
            for (Long productId : productIds) {
                if (coupon.getProducts().contains(productId)) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {
                throw new UnableServiceException("所购买的商品无法使用该优惠券");
            }
        }
        if (coupon.getTargetPrice() > actualPrice) {
            throw new UnableServiceException("金额不足，无法使用该优惠券");
        }
        return coupon.getPrice();
    }

    /**
     * 获取优惠券信息
     *
     * @param userId
     * @param couponId
     * @return
     */
    private Coupon getCoupon(Long userId, Long couponId) {
        if (couponId != null && couponId != 0) {
            Coupon coupon = couponService.getUseAbleCouponInfo(userId, couponId);
            if (coupon == null) {
                throw new NotExistException("查找不到对应的优惠券记录");
            }
            return coupon;
        }
        return null;
    }

    /**
     * 获取折扣价
     *
     * @param detail
     * @return
     */
    private Long getDiscountPrice(ProductDetail detail, Long originalPrice) {
        Map<Long, Long> discountMap = discountService.getDiscountOfProducts(detail.getBusinessId(), new Long[]{detail.getId()});
        if (discountMap.get(detail.getId()) != null) {
            return originalPrice * discountMap.get(detail.getId()) / 100;
        }
        return originalPrice;
    }

    @Transactional
    public Long doCreate(Order order) {
        orderMapper.create(order);
        if (order.getCouponId() != null && order.getCouponId() != 0) {
            couponService.useCoupon(order.getUserId(), order.getCouponId(), order.getId());
        }

        for (OrderItem item : order.getItems()) {
            item.setOrderId(order.getId());
            //TODO 魔术数字，后续修改
            if (item.getStockType() == 1) { //拍下减库存
                //库存操作
                stockService.book(item.getProductId(), order.getId(), item.getSpecificationId(), (long) item.getNum());
            }

            //添加订单项
            orderMapper.createItem(item);
        }
        return order.getId();
    }

    @Override
    public List<Order> selectOrders(OrderSearchParam param) {
        return orderMapper.selectOrders(param);
    }
}
