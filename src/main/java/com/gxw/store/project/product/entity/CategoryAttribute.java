package com.gxw.store.project.product.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 类目属性
 */
public class CategoryAttribute {
    private Long id;
    @NotNull(message = "categoryId不能为空")
    private Long categoryId;

    @NotBlank(message = "属性名称不能为空")
    @Length(min = 1, max = 5, message = "属性名称在1-5")
    private String name;

    @NotNull(message = "类型不能为空")
    @Range(min = 1,max = 2,message = "类型值错误")
    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
