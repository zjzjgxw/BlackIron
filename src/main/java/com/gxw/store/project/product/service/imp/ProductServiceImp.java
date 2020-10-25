package com.gxw.store.project.product.service.imp;

import com.gxw.store.project.common.utils.FileUtils;
import com.gxw.store.project.common.utils.StringUtils;
import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.ProductDetailImg;
import com.gxw.store.project.product.entity.ProductDetailMainImg;
import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.mapper.ProductMapper;
import com.gxw.store.project.product.service.ProductService;
import com.gxw.store.project.product.service.StockService;
import com.gxw.store.project.sale.service.DiscountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private StockService stockService;

    @Resource
    private DiscountService discountService;

    @Override
    @Transactional
    public Long createProductDetail(ProductDetail detail) {
        productMapper.createDetail(detail);
        productMapper.createAttributes(detail.getId(), detail.getAttributes());
        return detail.getId();
    }

    @Override
    @Transactional
    public Long createProductImages(Long detailId, ProductImages productImages) {
        productMapper.createMainImages(detailId, productImages.getMainImages());
        productMapper.createDetailImages(detailId, productImages.getDetailImages());
        return detailId;
    }

    @Override
    public ProductDetail getDetailById(Long id) {
        //TODO 访问计数更新

        ProductDetail detail = productMapper.getDetailById(id);

        List<ProductDetailImg> images = detail.getDetailImages();
        for (ProductDetailImg image : images) {
            if (StringUtils.isNotEmpty(image.getImgUrl())) {
                image.setImgUrl(FileUtils.getPath(image.getImgUrl()));
            }
        }
        List<ProductDetailMainImg> mainImages = detail.getMainImages();
        for (ProductDetailMainImg mainImage : mainImages) {
            if (StringUtils.isNotEmpty(mainImage.getImgUrl())) {
                mainImage.setImgUrl(FileUtils.getPath(mainImage.getImgUrl()));
            }
        }

        //依靠库存服务获取价格库存信息
        Map<Long, StockInfo> res = stockService.getProductPrice(id);
        StockInfo info = res.get(id);
        BigDecimal originalPrice = BigDecimal.valueOf(info.getPrice().doubleValue() / 100);
        BigDecimal price = BigDecimal.valueOf(info.getPrice().doubleValue() / 100);//库存中价格存储单位为分，转为元除100

        Map<Long, Long> discountMap = discountService.getDiscountOfProducts(detail.getBusinessId(), new Long[]{id});
        if (discountMap.get(detail.getId()) != null) {
            price = BigDecimal.valueOf(info.getPrice().doubleValue() * discountMap.get(detail.getId()) / 10000); //优惠折扣需要再除100
        }
        if (info != null) {
            detail.setPrice(price);
            detail.setOriginalPrice(originalPrice);
            detail.setSaleNum(info.getSaleNum());
            detail.setLastNum(info.getLastNum());
        }
        return detail;
    }

    @Override
    public List<ProductDetail> selectProducts(Long businessId, Long categoryId) {
        List<ProductDetail> details = productMapper.selectProducts(businessId, categoryId);
        List<Long> productIds = new ArrayList<>();
        for (ProductDetail detail : details) {
            detail.setCoverUrl(FileUtils.getPath(detail.getCoverUrl()));
            productIds.add(detail.getId());
        }
        Map<Long, StockInfo> stockInfoMap = stockService.getProductPrice(productIds.toArray(new Long[0]));
        //获取优惠信息
        Map<Long, Long> discountMap = discountService.getDiscountOfProducts(businessId, productIds.toArray(new Long[0]));
        for (ProductDetail detail : details) {
            StockInfo info = stockInfoMap.get(detail.getId());
            BigDecimal originalPrice = BigDecimal.valueOf(info.getPrice().doubleValue() / 100);
            BigDecimal price = BigDecimal.valueOf(info.getPrice().doubleValue() / 100);//库存中价格存储单位为分，转为元除100

            if (discountMap.get(detail.getId()) != null) {
                price = BigDecimal.valueOf(info.getPrice().doubleValue() * discountMap.get(detail.getId()) / 10000); //优惠折扣需要再除100
            }

            if (info != null) {
                detail.setPrice(price);
                detail.setOriginalPrice(originalPrice);
                detail.setSaleNum(info.getSaleNum());
                detail.setLastNum(info.getLastNum());
            }
        }

        return details;
    }


}
