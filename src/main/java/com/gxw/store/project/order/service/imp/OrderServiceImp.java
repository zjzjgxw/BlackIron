package com.gxw.store.project.order.service.imp;

import com.alibaba.fastjson.JSON;
import com.gxw.store.project.common.utils.exception.MissSpecificationException;
import com.gxw.store.project.common.utils.exception.NotExistException;
import com.gxw.store.project.common.utils.exception.UnEnoughStockException;
import com.gxw.store.project.common.utils.exception.UnableServiceException;
import com.gxw.store.project.order.dto.OrderSearchParam;
import com.gxw.store.project.order.entity.Express;
import com.gxw.store.project.order.entity.Order;
import com.gxw.store.project.order.entity.OrderItem;
import com.gxw.store.project.order.entity.OrderStatus;
import com.gxw.store.project.order.mapper.OrderMapper;
import com.gxw.store.project.order.service.ExpressService;
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
import com.gxw.store.project.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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
    private UserService userService;

    @Resource
    private DiscountService discountService;

    @Resource
    private CouponService couponService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ExpressService expressService;

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
        Long expressPrice = 9999999L;
        LinkedList<Long> productIds = new LinkedList<>();
        for (OrderItem item : items) {
            //获取产品信息
            ProductDetail detail = productService.getDetailById(item.getProductId());
            if(detail == null){
                throw new MissSpecificationException(); //缺少规格信息
            }
            productIds.add(item.getProductId());
            item.setName(detail.getName());
            item.setCoverUrl(detail.getCoverUrl());
            item.setProductSnap(JSON.toJSONString(detail));
            item.setStockType(detail.getStockType());
            //获取库存信息
            StockInfo stockInfo = stockService.getStockInfoByProductId(item.getProductId());
            if(stockInfo == null){
                throw new MissSpecificationException(); //缺少规格信息
            }
            if (expressPrice > stockInfo.getExpressPrice()) {
                expressPrice = stockInfo.getExpressPrice();
            }
            if (item.getSpecificationId() != null && stockInfo.getSpecifications().size() == 0) {
                throw new MissSpecificationException(); //缺少规格信息
            }
            if (item.getSpecificationId() == null && stockInfo.getSpecifications().size() == 0) { //不存在规格的情况
                if (item.getNum() > stockInfo.getLastNum()) {  //商品没有规格时，缺少库存
                    throw new UnEnoughStockException();
                }
                item.setOriginalPrice(stockInfo.getPrice());
            }
            if (item.getSpecificationId() != null) { //存在规格的情况
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
                        item.setOriginalPrice(specification.getDetail().getPrice()); //设置原价
                    }
                }
                if (item.getOriginalPrice() == null) {
                    throw new MissSpecificationException(); //缺少规格信息
                }
            }
            //根据促销活动那个获得实际价格
            item.setPrice(getDiscountPrice(detail, item.getOriginalPrice()));
            totalPrice += item.getOriginalPrice() * item.getNum();
            actualPrice += item.getPrice() * item.getNum();
        }

        order.setOriginalPrice(totalPrice);
        order.setExpressPrice(expressPrice);
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

        //获取商品折扣
        Map<Long, Long> discountMap = discountService.getDiscountOfProducts(detail.getBusinessId(), new Long[]{detail.getId()});
        if (discountMap.get(detail.getId()) != null) {
            return originalPrice * discountMap.get(detail.getId()) / 100;
        }

        //获取Vip折扣
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
    public List<Long> getOrderIds(OrderSearchParam param) {
        return orderMapper.getOrderIds(param);
    }

    @Override
    public List<Order> getDetailOfOrders(List<Long> ids) {
        if(ids.isEmpty()){
            return Collections.emptyList();
        }
        List<Order> orders = orderMapper.selectOrders(ids);
        List<Express> expresses = expressService.select();
        for(Order order : orders){
            for(Express express : expresses){
                if(order.getExpressId().equals(express.getId())){
                    order.setExpressName(express.getName());
                }
            }
        }
        return orders;
    }

    @Override
    public Order getOrder(Long id, Long businessId) {
        Order order = orderMapper.getOrder(id, businessId);
        order.setExpressName(expressService.getExpressName(order.getExpressId()));
        return order;
    }

    @Override
    @Transactional
    public Boolean paid(String code) {
        Order order = orderMapper.getOrderByCode(code);
        if (order == null || order.getStatus() != OrderStatus.UNPAID) {
            return false;
        }

        order.setStatus(OrderStatus.WAIT_SEND);
        for (OrderItem item : order.getItems()) {
            if (item.getStockType() == 2) { //付款减库存
                //库存操作
                stockService.book(item.getProductId(), order.getId(), item.getSpecificationId(), (long) item.getNum());
            }
        }
        order.setPayTime(new Date());
        orderMapper.update(order);

        //增加用户消费金额,和积分。
        userService.addConsumePrice(order.getUserId(),order.getPrice());
        userService.addPoint(order.getUserId(),order.getPrice());
        userService.updateVip(order.getUserId());
        return true;
    }

    @Override
    public Boolean send(Long businessId, Long orderId, Long expressId, String expressCode) {
        Order order = orderMapper.getOrder(orderId, businessId);
        if (order == null || order.getStatus() != OrderStatus.WAIT_SEND) {
            throw new NotExistException("查找不到对应的待发货订单");
        }
        order.setExpressId(expressId);
        order.setExpressCode(expressCode);
        order.setSendTime(new Date());

        order.setStatus(OrderStatus.HAS_SEND);
        orderMapper.update(order);

        //TODO 可以加短信通知收件人已发货
        return true;
    }

    @Override
    public Boolean updateExpressInfo(Long businessId, Long orderId, Long expressId, String expressCode) {
        Order order = orderMapper.getOrder(orderId, businessId);
        if (order == null) {
            throw new NotExistException("查找不到对应的订单");
        }
        order.setExpressId(expressId);
        order.setExpressCode(expressCode);
        orderMapper.update(order);
        return true;
    }

    @Override
    public Boolean hasOrderOfUser(Long orderId, Long productId, Long userId) {
        return orderMapper.hasOrderOfUser(orderId, productId, userId) > 0;
    }

    @Override
    public Boolean finished(Long orderId, Long businessId) {
        Order order = orderMapper.getOrder(orderId, businessId);
        if (order == null ) {
            throw new NotExistException("查找不到对应的订单");
        }
        if(order.getStatus() == OrderStatus.HAS_SEND || order.getStatus() == OrderStatus.WAIT_COMMENT){
            order.setStatus(OrderStatus.FINISHED);
        }else{
            throw new NotExistException("查找不到对应的发货订单");
        }
        orderMapper.update(order);
        return true;
    }
}
