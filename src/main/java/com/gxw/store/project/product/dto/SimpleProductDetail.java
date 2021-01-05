package com.gxw.store.project.product.dto;

/**
 * 商品基本信息
 */
public class SimpleProductDetail {

    private Long id;

    private String name;

    private String coverUrl; //封面图片


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

}
