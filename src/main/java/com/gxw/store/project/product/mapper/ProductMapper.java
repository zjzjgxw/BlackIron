package com.gxw.store.project.product.mapper;


import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.ProductDetailAttribute;
import com.gxw.store.project.product.entity.ProductDetailImg;
import com.gxw.store.project.product.entity.ProductDetailMainImg;

import java.util.List;

public interface ProductMapper {
    void createDetail(ProductDetail detail);

    int updateDetail(ProductDetail detail);

    void createAttributes(Long detailId, List<ProductDetailAttribute> attributes);

    /**
     * 删除某个属性
     *
     * @param id  属性id
     * @param detailId 商品id
     * @return
     */
    int deleteAttribute(Long id, Long detailId);


    /**
     * 创建详情描述图
     *
     * @param detailId
     * @param images
     */
    void createDetailImages(Long detailId, List<ProductDetailImg> images);

    /**
     * 删除描述信息
     */
    int deleteDetailImages(Long detailId);

    /**
     * 创建主图
     *
     * @param detailId
     * @param images
     */
    void createMainImages(Long detailId, List<ProductDetailMainImg> images);

    /**
     * 删除主图
     * @param detailId
     * @return
     */
    int deleteMainImages(Long detailId);

    /**
     * 获取详情描述信息
     *
     * @param id
     * @return
     */
    ProductDetail getDetailById(Long id);


    List<ProductDetail> selectProducts(Long businessId, Long categoryId);

}
