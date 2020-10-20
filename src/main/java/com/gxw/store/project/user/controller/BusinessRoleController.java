package com.gxw.store.project.user.controller;

import com.fasterxml.jackson.annotation.JsonView;

import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.view.ViewUtils;
import com.gxw.store.project.user.dto.RolePermissionRel;
import com.gxw.store.project.user.entity.business.BusinessRole;
import com.gxw.store.project.user.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/businesses/role")
public class BusinessRoleController {

    @Autowired
    private BusinessService businessService;

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody BusinessRole role){
        Long id = businessService.createRole(role);
        HashMap<String,Long> res= new HashMap<>();
        res.put("id",id);
        return ResponseResult.success(res);
    }

    @GetMapping()
    public ResponseResult getRoles(@RequestParam("business_id") Long businessId){
        List<BusinessRole> roles = businessService.getRoles(businessId);
        HashMap<String, List<BusinessRole>> res= new HashMap<>();
        res.put("roles",roles);
        return ResponseResult.success(res);
    }


    @GetMapping("/{roleId}")
    @JsonView(ViewUtils.Simple.class)
    public ResponseResult getRole(@PathVariable Long roleId, @RequestParam("business_id") Long businessId){
        BusinessRole role = businessService.getRoleInfo(roleId,businessId);
        HashMap<String, BusinessRole> res= new HashMap<>();
        res.put("role",role);
        return ResponseResult.success(res);
    }

    @DeleteMapping("/{roleId}")
    public ResponseResult deleteRole(@PathVariable Long roleId){
        int id = businessService.deleteRoleById(roleId);
        if(id == 0){
            return ResponseResult.error("删除失败");
        }
        return ResponseResult.success();
    }

    @PostMapping("/permissions")
    public ResponseResult saveRolePermissions(@Valid @RequestBody RolePermissionRel rolePermissionRel){
        businessService.saveRolePermissions(rolePermissionRel.getRoleId(),rolePermissionRel.getPermissions());
        return ResponseResult.success();
    }
}
