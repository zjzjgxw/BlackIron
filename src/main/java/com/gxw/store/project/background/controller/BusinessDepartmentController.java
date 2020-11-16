package com.gxw.store.project.background.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.view.ViewUtils;
import com.gxw.store.project.user.entity.business.BusinessDepartment;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/businesses/department")
public class BusinessDepartmentController {

    @Autowired
    private BusinessService businessService;

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody BusinessDepartment department){
        Long id = businessService.createDepartment(department);
        HashMap<String,Long> res= new HashMap<>();
        res.put("id",id);
        return ResponseResult.success(res);
    }

    @GetMapping()
    public ResponseResult getDepartments(@RequestParam("business_id") Long business_id){
        List<BusinessDepartment> departments = businessService.getDepartments(business_id);
        HashMap<String, List<BusinessDepartment>> res= new HashMap<>();
        res.put("departments",departments);
        return ResponseResult.success(res);
    }

    @GetMapping("/{departmentId}")
    @JsonView(ViewUtils.Simple.class)
    public ResponseResult getDepartment(@PathVariable Long departmentId, @RequestParam("business_id") Long business_id ){
        BusinessDepartment department = businessService.getDepartmentInfo(departmentId,business_id);
        HashMap<String, BusinessDepartment> res= new HashMap<>();
        res.put("department",department);
        return ResponseResult.success(res);
    }

    @DeleteMapping("/{department_id}")
    public ResponseResult deleteDepartment(@PathVariable Long department_id){
        int id = businessService.deleteDepartmentById(department_id);
        if(id == 0){
            return ResponseResult.error("删除失败");
        }
        return ResponseResult.success();
    }
}
