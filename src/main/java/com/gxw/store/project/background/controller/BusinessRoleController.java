package com.gxw.store.project.background.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.common.utils.view.ViewUtils;
import com.gxw.store.project.user.dto.GroupPermissionRel;
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
        role.setBusinessId(SessionUtils.getBusinessId());
        Long id = businessService.createRole(role);
        HashMap<String,Long> res= new HashMap<>();
        res.put("id",id);
        return ResponseResult.success(res);
    }

    @GetMapping()
    public ResponseResult getRoles(){
        List<BusinessRole> roles = businessService.getRoles(SessionUtils.getBusinessId());
        HashMap<String, List<BusinessRole>> res= new HashMap<>();
        res.put("roles",roles);
        return ResponseResult.success(res);
    }

    @PutMapping()
    public ResponseResult updateRole(@RequestBody BusinessRole role){
        role.setBusinessId(SessionUtils.getBusinessId());
        if(businessService.updateRole(role)){
            return ResponseResult.success();
        }else{
            return ResponseResult.error();
        }
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
       if( businessService.deleteRoleById(roleId,SessionUtils.getBusinessId())){
           return ResponseResult.success();
       }else{
           return ResponseResult.error();
       }
    }

    @GetMapping("/permissions")
    public ResponseResult getRolePermissions(@RequestParam Long roleId){
        Long businessId = SessionUtils.getBusinessId();

        List<GroupPermissionRel> rels = businessService.getPermissionsOfRole(roleId,businessId);
        HashMap<Long,List<Long>> res = new HashMap<>();
        for(GroupPermissionRel rel : rels){
            res.put(rel.getGroupId(),rel.getPermissions());
        }
        return ResponseResult.success(res) ;
    }

    @PostMapping("/permissions")
    public ResponseResult saveRolePermissions(@Valid @RequestBody RolePermissionRel rolePermissionRel){
        businessService.saveRolePermissions(rolePermissionRel.getRoleId(),rolePermissionRel.getPermissions());
        return ResponseResult.success();
    }
}
