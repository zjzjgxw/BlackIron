package com.gxw.store.project.product.mapper;



import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.ProductDetailAttribute;
import com.gxw.store.project.product.entity.ProductDetailImg;
import com.gxw.store.project.product.entity.ProductDetailMainImg;

import java.util.List;

public interface ProductMapper {
    void createDetail(ProductDetail detail);

    void createAttributes(Long detailId, List<ProductDetailAttribute> attributes);


    /**
     * 创建详情描述图
     * @param detailId
     * @param images
     */
    void createDetailImages(Long detailId, List<ProductDetailImg> images);

    /**
     * 创建主图
     * @param detailId
     * @param images
     */
    void createMainImages(Long detailId, List<ProductDetailMainImg> images);

    /**
     * 获取详情描述信息
     * @param id
     * @return
     */
    ProductDetail getDetailById(Long id);

}
