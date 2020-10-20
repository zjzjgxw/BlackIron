package com.gxw.store.project.product.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 规格选项
 */
public class SpecificationOption {
    private Long id;
    @NotNull(message = "specificationId不能为空")
    private Long specificationId;

    @NotBlank(message = "缺少属性内容")
    @Length(min = 1, max = 5, message = "属性内容长度在1-5")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
