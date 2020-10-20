package com.gxw.store.project.product.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CategorySpecification {
    private Long id;
    @NotNull(message = "categoryId不能为空")
    private Long categoryId;

    @NotBlank(message = "属性规格不能为空")
    @Length(min = 1, max = 5, message = "属性规格长度在1-5")
    private String name;

    private List<SpecificationOption> options;

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

    public List<SpecificationOption> getOptionList() {
        return options;
    }

    public void setOptionList(List<SpecificationOption> optionList) {
        this.options = optionList;
    }
}
