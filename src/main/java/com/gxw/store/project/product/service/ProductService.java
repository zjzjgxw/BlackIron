package com.gxw.store.project.product.service;

import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.ProductDetailAttribute;

import java.util.List;

public interface ProductService {

    /**
     * 创建商品详情基础信息
     *
     * @param detail
     * @return
     */
    Long createProductDetail(ProductDetail detail);

    /**
     * 创建商品图片信息
     *
     * @param detailId
     * @param productImages
     * @return
     */
    Long createProductImages(Long detailId, ProductImages productImages);


    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    ProductDetail getDetailById(Long id);


    /**
     * 获取商品列表
     *
     * @return
     */
    List<ProductDetail> selectProducts(Long businessId, Long categoryId);

    /**
     * 更新商品信息
     *
     * @param detail
     * @return
     */
    Boolean updateDetail(ProductDetail detail);

    /**
     * 删除某个属性
     * @param id 属性id
     * @param detailId 商品id
     * @return
     */
    Boolean removeAttribute(Long id, Long detailId);

    /**
     * 新增属性
     *
     * @param detailId
     * @param attributes
     * @return
     */
    Boolean addAttributes(Long detailId, List<ProductDetailAttribute> attributes);

    /**
     * 修个商品图片信息
     * @param detailId
     * @param productImages
     * @return
     */
    Boolean updateProductImages(Long detailId, ProductImages productImages);

}
