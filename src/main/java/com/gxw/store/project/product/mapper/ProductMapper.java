package com.gxw.store.project.product.mapper;


import com.gxw.store.project.product.dto.ProductSearchParams;
import com.gxw.store.project.product.entity.*;

import java.util.List;

public interface ProductMapper {
    void createDetail(ProductDetail detail);

    int updateDetail(ProductDetail detail);

    int deleteDetail(Long id, Long businessId);

    void createAttributes(Long detailId, List<ProductDetailAttribute> attributes);

    /**
     * 删除某个属性
     *
     * @param id       属性id
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
     *
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


    List<ProductDetail> selectProducts(ProductSearchParams params);


    /**
     * 新增推荐
     *
     * @param recommends
     */
    void addRecommend(List<ProductRecommend> recommends);


    /**
     * 删除推荐
     *
     * @param businessId
     * @param productIds
     * @return
     */
    int deleteRecommend(Long businessId, List<Long> productIds);

    /**
     * 更新推荐
     *
     * @param recommend
     * @return
     */
    int updateRecommend(ProductRecommend recommend);

    /**
     * 获取推荐的商品列表
     *
     * @param businessId
     * @return
     */
    List<ProductDetail> getRecommendProducts(Long businessId);

}
