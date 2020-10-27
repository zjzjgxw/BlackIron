package com.gxw.store.project.product.dto;

import com.gxw.store.project.product.entity.ProductDetailAttribute;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddProductAttributes {

    @NotNull
    Long detailId;
    @NotNull
    List<ProductDetailAttribute> attributes;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public List<ProductDetailAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductDetailAttribute> attributes) {
        this.attributes = attributes;
    }
}
