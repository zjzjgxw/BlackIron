package com.gxw.store.project.common.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.gxw.store.project.common.utils.SqlUtil;
import com.gxw.store.project.common.utils.StringUtils;
import com.gxw.store.project.common.utils.page.PageDataInfo;
import com.gxw.store.project.common.utils.page.PageDomain;
import com.gxw.store.project.common.utils.page.PageSupport;
import com.gxw.store.project.common.utils.page.SimplePageDataInfo;
import com.gxw.store.project.common.utils.view.ViewUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = PageSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    protected PageDataInfo getDataTable(List<?> list) {
        PageDataInfo rspData = new PageDataInfo();
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    protected PageDataInfo getDataTable(List<?> list, Class<?> view) {
        PageDataInfo rspData = null;
        if(view == ViewUtils.Simple.class){
             rspData = new SimplePageDataInfo();
        }else{
             rspData = new PageDataInfo();
        }
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

}
