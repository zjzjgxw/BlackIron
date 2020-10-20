package com.gxw.store.project.product.dto;

import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.entity.StockSpecification;

import java.util.List;

public class StockUpdateInfo extends StockInfo {
    private List<StockSpecification> newSpecifications; //新增的规格信息

    public List<StockSpecification> getNewSpecifications() {
        return newSpecifications;
    }

    public void setNewSpecifications(List<StockSpecification> newSpecifications) {
        this.newSpecifications = newSpecifications;
    }
}
