package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.product.dto.StockUpdateInfo;
import com.gxw.store.project.product.entity.StockInfo;
import com.gxw.store.project.product.service.StockService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/stock")
public class StockController extends BaseController {

    @Resource
    private StockService stockService;


    @PostMapping
    public ResponseResult create(@Valid @RequestBody StockInfo info) {
        Long id = stockService.create(info);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping
    public ResponseResult getStockInfo(@RequestParam Long productId) {
        StockInfo info = stockService.getStockInfoByProductId(productId);
        HashMap<String, StockInfo> res = new HashMap<>();
        res.put("info", info);
        return ResponseResult.success(res);
    }


    /**
     * 删除某种规格的库存
     * @param id
     * @return
     */
    @DeleteMapping("/specification/{id}")
    public ResponseResult deleteStockSpecification(@PathVariable Long id){
        stockService.deleteStockSpecification(id);
        return ResponseResult.success();
    }


    @PutMapping
    public ResponseResult update(@Valid @RequestBody StockUpdateInfo info){
        stockService.updateStockInfo(info);
        return ResponseResult.success();
    }

}
