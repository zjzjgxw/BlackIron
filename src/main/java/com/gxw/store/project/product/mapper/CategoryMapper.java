package com.gxw.store.project.product.mapper;


import com.gxw.store.project.product.entity.*;

import java.util.List;

public interface CategoryMapper {
    void create(Category category);

    void createAttribute(CategoryAttribute categoryAttribute);

    void createAttributeOption(AttributeOption attributeOption);

    List<AttributeOption> selectAttributeOptions(Long attributeId);

    /**
     * 删除属性选项
     * @param id
     */
    void deleteAttributeOption(Long id);


    /**
     * 更新属性内容
     * @param attributeOption
     */
    void updateAttributeOption(AttributeOption attributeOption);


    /**
     * 创建规格
     * @param categorySpecification
     */
    void createSpecification(CategorySpecification categorySpecification);

    /**
     * 创建规格选项
     * @param option
     */
    void createSpecificationOption(SpecificationOption option);

    /**
     * 修改规格选项
     * @param option
     * @return
     */
    void updateSpecificationOption(SpecificationOption option);


    /**
     * 删除规格选项
     * @param id
     * @return
     */
    void deleteSpecificationOption(Long id);


    /**
     * 获取类目下的规格信息
     * @param categoryId 类目id
     * @return
     */
    List<CategorySpecification> selectCategorySpecifications(Long categoryId);

}
