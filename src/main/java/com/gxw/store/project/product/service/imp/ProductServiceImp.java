package com.gxw.store.project.product.service.imp;

import com.gxw.store.project.common.utils.FileUtils;
import com.gxw.store.project.product.dto.ProductImages;
import com.gxw.store.project.product.entity.ProductDetail;
import com.gxw.store.project.product.entity.ProductDetailImg;
import com.gxw.store.project.product.entity.ProductDetailMainImg;
import com.gxw.store.project.product.mapper.ProductMapper;
import com.gxw.store.project.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    @Transactional
    public Long createProductDetail(ProductDetail detail) {
        productMapper.createDetail(detail);
        productMapper.createAttributes(detail.getId(),detail.getAttributes());
        return detail.getId();
    }

    @Override
    @Transactional
    public Long createProductImages(Long detailId, ProductImages productImages) {
        productMapper.createMainImages(detailId,productImages.getMainImages());
        productMapper.createDetailImages(detailId,productImages.getDetailImages());
        return detailId;
    }

    @Override
    public ProductDetail getDetailById(Long id) {
        //TODO 访问计数更新

        ProductDetail detail = productMapper.getDetailById(id);

        List<ProductDetailImg> images = detail.getDetailImages();
        for (int i =0; i < images.size(); i++){
            images.get(i).setImgUrl(FileUtils.getPath(images.get(i).getImgUrl()));
        }

        List<ProductDetailMainImg> mainImages = detail.getMainImages();
        for (int i =0; i < mainImages.size(); i++){
            mainImages.get(i).setImgUrl(FileUtils.getPath(mainImages.get(i).getImgUrl()));
        }

        return detail;
    }

}
