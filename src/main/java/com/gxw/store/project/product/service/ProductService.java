package com.gxw.store.project.product.service;

import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.entity.ProductDetail;

import java.util.List;

public interface ProductService {

    /**
     * 创建商品详情基础信息
     * @param detail
     * @return
     */
    Long createProductDetail(ProductDetail detail);

    /**
     * 创建商品图片信息
     * @param detailId
     * @param productImages
     * @return
     */
    Long createProductImages(Long detailId, ProductImages productImages);


    /**
     * 获取商品详情
     * @param id
     * @return
     */
    ProductDetail getDetailById(Long id);


    /**
     * 获取商品列表
     * @return
     */
    List<ProductDetail> selectProducts(Long businessId,Long categoryId);
}
