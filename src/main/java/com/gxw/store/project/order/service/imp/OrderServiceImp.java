package com.gxw.store.project.order.service.imp;

import com.alibaba.fastjson.JSON;
import com.gxw.store.project.common.utils.exception.MissSpecificationException;
import com.gxw.store.project.common.utils.exception.UnEnoughStockException;
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
import com.gxw.store.project.user.entity.business.Business;
import com.gxw.store.project.user.service.BusinessService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        for (OrderItem item : items) {
            //获取产品信息
            ProductDetail detail = productService.getDetailById(item.getProductId());
            item.setName(detail.getName());
            item.setCoverUrl(detail.getCoverUrl());
            item.setProductSnap(JSON.toJSONString(detail));
            item.setStockType(detail.getStockType());
            //获取库存信息
            StockInfo stockInfo = stockService.getStockInfoByProductId(item.getProductId());
            if (item.getSpecificationId() == 0 && stockInfo.getSpecifications().size() != 0) {
                throw new MissSpecificationException(); //缺少规格信息
            }
            if (item.getSpecificationId() == 0 && stockInfo.getSpecifications().size() == 0) {
                if (item.getNum() > stockInfo.getLastNum()) {  //商品没有规格时，缺少库存
                    throw new UnEnoughStockException();
                }
                item.setOriginalPrice(stockInfo.getPrice());
                //TODO 后续增加优惠策略
                item.setPrice(stockInfo.getPrice());
            }
            if (item.getSpecificationId() != 0) {
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
                        //TODO 后续增加优惠策略
                        item.setPrice(item.getOriginalPrice()); //设置实际价格
                    }
                }
            }
            totalPrice += item.getOriginalPrice();
        }

        order.setOriginalPrice(totalPrice);
        //TODO 后续添加快递费
        order.setExpressPrice(500L);
        //TODO 后续增加优惠策略
        order.setPrice(totalPrice + order.getExpressPrice());

        return proxySelf.doCreate(order);
    }

    @Transactional
    public Long doCreate(Order order) {
        orderMapper.create(order);
        for (OrderItem item : order.getItems()) {
            item.setOrderId(order.getId());
            //TODO 魔术数字，后续修改
            if(item.getStockType() == 1){ //拍下减库存
                //库存操作
                stockService.book(item.getProductId(),item.getSpecificationId(), (long) item.getNum());
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
