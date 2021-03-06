package com.gxw.store.project.product.service;

import com.gxw.store.project.product.entity.*;

import java.util.List;

public interface CategoryService {

    Long create(Category category);

    /**
     * 获取类别列表
     * @param businessId
     * @return
     */
    List<Category> getCategories(Long businessId);

    /**
     * 删除类目
     * @param id
     * @param businessId
     * @return
     */
    Boolean deleteCategory(Long id, Long businessId);


    Boolean updateCategory(Category category);

    /**
     * 创建类的属性
     * @param categoryAttribute
     * @return
     */
    Long createAttribute(CategoryAttribute categoryAttribute);

    /**
     * 获取属性列表
     * @param categoryId
     * @return
     */
    List<CategoryAttribute> getAttributes(Long categoryId);


    /**
     * 修改属性名称
     * @param id
     * @param name
     * @return
     */
    Boolean updateAttribute(Long id, String name);


    Boolean deleteAttribute(Long id);

    /**
     * 创建属性选择内容
     * @param attributeOption
     * @return
     */
    Long createAttributeOption(AttributeOption attributeOption);


    /**
     * 搜索属性选择项
     * @param attributeId
     * @return
     */
    List<AttributeOption> selectAttributeOptions(Long attributeId);

    /**
     * 删除属性选项
     * @param id
     * @return
     */
    boolean deleteAttributeOption(Long id);


    /**
     * 更新属性选项
     * @param attributeOption
     * @return
     */
    boolean updateAttributeOption(AttributeOption attributeOption);

    /**
     * 创建规格
     * @param categorySpecification
     * @return
     */
    Long createSpecification(CategorySpecification categorySpecification);

    /**
     * 创建规格选项
     * @param option
     * @return
     */
    Long createSpecificationOption(SpecificationOption option);

    /**
     * 修改规格选项
     * @param option
     * @return
     */
    boolean updateSpecificationOption(SpecificationOption option);


    /**
     * 删除规格选项
     * @param id
     * @return
     */
    boolean deleteSpecificationOption(Long id);

    /**
     * 获取某个类目下的规格信息
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
    Boolean updateSpecification(Long id, String name);


    /**
     * 删除规格
     * @param id
     * @return
     */
    Boolean deleteSpecification(Long id);
}
