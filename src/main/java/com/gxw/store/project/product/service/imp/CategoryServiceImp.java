package com.gxw.store.project.product.service.imp;

import com.gxw.store.project.common.utils.FileUtils;
import com.gxw.store.project.common.utils.exception.HasExistException;
import com.gxw.store.project.product.entity.*;
import com.gxw.store.project.product.mapper.CategoryMapper;
import com.gxw.store.project.product.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Category> categories = categoryMapper.getCategories(businessId);
        for (Category category : categories) {
            category.setImgPath(category.getImgUrl());
            if(!category.getImgUrl().isEmpty()){
                category.setImgUrl(FileUtils.getPath(category.getImgUrl()));
            }
        }
        return categories;
    }

    @Override
    public Boolean deleteCategory(Long id, Long businessId) {
        int row = categoryMapper.deleteCategory(id, businessId);
        return row != 0;
    }

    @Override
    public Boolean updateCategory(Category category) {
        int row = categoryMapper.updateCategory(category);
        return row != 0;
    }
    //  ----------  属性   ------------//

    @Override
    public Long createAttribute(CategoryAttribute categoryAttribute) {
        categoryMapper.createAttribute(categoryAttribute);
        return categoryAttribute.getId();
    }

    @Override
    public List<CategoryAttribute> getAttributes(Long categoryId) {
        return categoryMapper.getAttributes(categoryId);
    }

    @Override
    public Boolean updateAttribute(Long id, String name) {
        int row = categoryMapper.updateAttributes(id, name);
        return row != 0;
    }

    @Override
    @Transactional
    public Boolean deleteAttribute(Long id) {
        int row = categoryMapper.deleteAttribute(id);
        categoryMapper.deleteOptionOfAttribute(id);
        return row != 0;
    }


    @Override
    public Long createAttributeOption(AttributeOption attributeOption) {
        int count = categoryMapper.hasAttributeOption(attributeOption);
        if (count > 0) {
            throw new HasExistException("选项已经存在");
        }
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
        int count = categoryMapper.hasSpecificationOption(option);
        if (count > 0) {
            throw new HasExistException("选项已经存在");
        }
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

    @Override
    public Boolean updateSpecification(Long id, String name) {
        int row = categoryMapper.updateSpecification(id, name);
        return row != 0;
    }

    @Override
    public Boolean deleteSpecification(Long id) {
        int row = categoryMapper.deleteSpecification(id);
        return row != 0;
    }


}
