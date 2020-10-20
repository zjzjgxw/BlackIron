package com.gxw.store.project.common.utils.page;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author ruoyi
 */
public class PageDataInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows;

    /**
     * 表格数据对象
     */
    public PageDataInfo()
    {
    }

    /**
     * 分页
     *
     * @param list 列表数据
     * @param total 总记录数
     */
    public PageDataInfo(List<?> list, int total)
    {
        this.rows = list;
        this.total = total;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public List<?> getRows()
    {
        return rows;
    }

    public void setRows(List<?> rows)
    {
        this.rows = rows;
    }


}