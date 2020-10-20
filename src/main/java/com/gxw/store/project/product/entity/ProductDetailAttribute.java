package com.gxw.store.project.product.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 商品属性
 */
public class ProductDetailAttribute {

    private Long id;

    private Long detailId;

    @NotBlank(message = "属性名不能为空")
    @Length(min = 1, max = 5, message = "属性名长度在1-5")
    private String name;

    @NotNull(message = "属性值不能为空")
    @Length(min = 1, max = 20, message = "属性值长度在1-20")
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
