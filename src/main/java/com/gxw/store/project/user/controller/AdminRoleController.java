package com.gxw.store.project.user.controller;

import com.fasterxml.jackson.annotation.JsonView;

import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.view.ViewUtils;
import com.gxw.store.project.user.dto.RolePermissionRel;
import com.gxw.store.project.user.entity.admin.AdminRole;
import com.gxw.store.project.user.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admins/roles")
public class AdminRoleController {
    @Resource
    private AdminService adminService;

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody AdminRole role) {
        Long id = adminService.createRole(role);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping("/{id}")
    @JsonView(ViewUtils.Simple.class)
    public ResponseResult getRole(@PathVariable Long id) {
        AdminRole role = adminService.getRole(id);
        HashMap<String, AdminRole> res = new HashMap<>();
        res.put("role", role);
        return ResponseResult.success(res);
    }

    @GetMapping()
    @JsonView(ViewUtils.Simple.class)
    public ResponseResult getRoles() {
        List<AdminRole> roles = adminService.getRoles();
        HashMap<String, List<AdminRole>> res = new HashMap<>();
        res.put("roles", roles);
        return ResponseResult.success(res);
    }

    @PutMapping()
    public ResponseResult updateRole(@Valid @RequestBody AdminRole role) {
        adminService.updateRole(role);
        return ResponseResult.success();
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable Long id) {
        adminService.deleteRole(id);
        return ResponseResult.success();
    }

    @PostMapping("/permissions")
    public ResponseResult saveRolePermissions(@Valid @RequestBody RolePermissionRel rolePermissionRel){
        adminService.saveRolePermissions(rolePermissionRel.getRoleId(),rolePermissionRel.getPermissions());
        return ResponseResult.success();
    }

}
