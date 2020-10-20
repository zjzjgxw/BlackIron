package com.gxw.store.project.product.dto;

import com.gxw.store.project.product.entity.ProductDetailImg;
import com.gxw.store.project.product.entity.ProductDetailMainImg;

import java.util.List;

public class ProductImages {

    List<ProductDetailImg> detailImages;

    List<ProductDetailMainImg> mainImages;

    public List<ProductDetailImg> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(List<ProductDetailImg> detailImages) {
        this.detailImages = detailImages;
    }

    public List<ProductDetailMainImg> getMainImages() {
        return mainImages;
    }

    public void setMainImages(List<ProductDetailMainImg> mainImages) {
        this.mainImages = mainImages;
    }
}
