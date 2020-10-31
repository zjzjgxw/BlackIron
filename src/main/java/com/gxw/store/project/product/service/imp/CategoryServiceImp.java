package com.gxw.store.project.product.service.imp;

import com.gxw.store.project.product.entity.*;
import com.gxw.store.project.product.mapper.CategoryMapper;
import com.gxw.store.project.product.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Long create(Category category) {
        categoryMapper.create(category);
        return category.getId();
    }

    @Override
    public List<Category> getCategories(Long businessId) {
        return categoryMapper.getCategories(businessId);
    }

    @Override
    public Boolean deleteCategory(Long id, Long businessId) {
        int row = categoryMapper.deleteCategory(id, businessId);
        return row != 0;
    }

    @Override
    public Boolean updateCategory(String name, Long id, Long businessId) {
        int row = categoryMapper.updateCategory(name, id, businessId);
        return row != 0;
    }
    //  ----------  属性   ------------//

    @Override
    public Long createAttribute(CategoryAttribute categoryAttribute) {
        categoryMapper.createAttribute(categoryAttribute);
        return categoryAttribute.getId();
    }


    @Override
    public Long createAttributeOption(AttributeOption attributeOption) {
        categoryMapper.createAttributeOption(attributeOption);
        return attributeOption.getId();
    }

    @Override
    public List<AttributeOption> selectAttributeOptions(Long attributeId) {
        return categoryMapper.selectAttributeOptions(attributeId);
    }

    @Override
    public boolean deleteAttributeOption(Long id) {
        categoryMapper.deleteAttributeOption(id);
        return true;
    }

    @Override
    public boolean updateAttributeOption(AttributeOption attributeOption) {
        categoryMapper.updateAttributeOption(attributeOption);
        return true;
    }

    // ------- 规格
    @Override
    public Long createSpecification(CategorySpecification categorySpecification) {
        categoryMapper.createSpecification(categorySpecification);
        return categorySpecification.getId();
    }

    @Override
    public Long createSpecificationOption(SpecificationOption option) {
        categoryMapper.createSpecificationOption(option);
        return option.getId();
    }

    @Override
    public boolean updateSpecificationOption(SpecificationOption option) {
        categoryMapper.updateSpecificationOption(option);
        return true;
    }

    @Override
    public boolean deleteSpecificationOption(Long id) {
        categoryMapper.deleteSpecificationOption(id);
        return true;
    }

    @Override
    public List<CategorySpecification> selectCategorySpecifications(Long categoryId) {
        return categoryMapper.selectCategorySpecifications(categoryId);
    }


}
