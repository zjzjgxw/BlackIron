package com.gxw.store.project.product.mapper;


import com.gxw.store.project.product.entity.*;

import java.util.List;

public interface CategoryMapper {
    void create(Category category);

    List<Category> getCategories(Long businessId);

    int deleteCategory(Long id, Long businessId);

    int updateCategory(Category category);

    void createAttribute(CategoryAttribute categoryAttribute);

    List<CategoryAttribute> getAttributes(Long categoryId);

    int updateAttributes(Long id, String name);

    int deleteAttribute(Long id);

    /**
     * 删除属性下的所有选项
     * @param attributeId
     * @return
     */
    int deleteOptionOfAttribute(Long attributeId);

    void createAttributeOption(AttributeOption attributeOption);


    /**
     * 查找是否有某种规格
     * @param option
     * @return
     */
    int hasAttributeOption(AttributeOption option);


    List<AttributeOption> selectAttributeOptions(Long attributeId);

    /**
     * 删除某一个属性选项
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
     * 查找是否有某种规格
     * @param option
     * @return
     */
    int hasSpecificationOption(SpecificationOption option);

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

    /**
     * 修改规格名称
     * @param id
     * @param name
     * @return
     */
    int updateSpecification(Long id, String name);


    /**
     * 删除规格
     * @param id
     * @return
     */
    int deleteSpecification(Long id);

}
