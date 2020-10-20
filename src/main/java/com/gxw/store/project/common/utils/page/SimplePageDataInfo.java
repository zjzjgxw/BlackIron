package com.gxw.store.project.common.utils.page;

import com.fasterxml.jackson.annotation.JsonView;
import com.gxw.store.project.common.utils.view.ViewUtils;

import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author ruoyi
 */
public class SimplePageDataInfo extends PageDataInfo
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    @JsonView(ViewUtils.Simple.class)
    private long total;

    /** 列表数据 */
    @JsonView(ViewUtils.Simple.class)
    private List<?> rows;

    /**
     * 表格数据对象
     */
    public SimplePageDataInfo()
    {
    }

    /**
     * 分页
     *
     * @param list 列表数据
     * @param total 总记录数
     */
    public SimplePageDataInfo(List<?> list, int total)
    {
        this.rows = list;
        this.total = total;
    }




}