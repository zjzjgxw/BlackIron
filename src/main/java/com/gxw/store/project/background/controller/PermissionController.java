package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.user.dto.PermissionSearchParams;
import com.gxw.store.project.user.entity.Permission;
import com.gxw.store.project.user.entity.PermissionGroup;
import com.gxw.store.project.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @NeedToken
    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Permission permission) {
        Long id = permissionService.create(permission);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping()
    public ResponseResult getAdmins(@RequestParam int type,
                                    @RequestParam(required = false) Long id,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) Long groupId) {
        startPage();
        List<Permission> permissions = null;
        PermissionSearchParams searchParams = new PermissionSearchParams(id, type, name, groupId);
        permissions = permissionService.getPermissions(searchParams);
        return ResponseResult.success(getDataTable(permissions));
    }

    @NeedToken
    @GetMapping("/{id}")
    public ResponseResult getPermission(@PathVariable Long id) {
        Permission permission = permissionService.selectPermissionById(id);
        HashMap<String, Permission> res = new HashMap<>();
        res.put("permission", permission);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping("/staffs")
    public ResponseResult getPermissionsByStaffId(@RequestParam Long staffId) {
        List<Permission> permissionList = permissionService.selectPermissionsByStaffId(staffId);
        HashMap<String, List<Permission>> res = new HashMap<>();
        res.put("permissions", permissionList);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping("/admins")
    public ResponseResult getPermissionsByAdminId(@RequestParam Long adminId) {
        List<Permission> permissionList = permissionService.selectPermissionsByAdminId(adminId);
        HashMap<String, List<Permission>> res = new HashMap<>();
        res.put("permissions", permissionList);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PutMapping()
    public ResponseResult updatePermission(@Valid @RequestBody Permission permission) {
        permissionService.updatePermission(permission);
        return ResponseResult.success();
    }

    @NeedToken
    @PostMapping("/groups")
    public ResponseResult createGroup(@Valid @RequestBody PermissionGroup permissionGroup) {
        Long id = permissionService.createGroup(permissionGroup);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PutMapping("/groups")
    public ResponseResult updateGroup(@Valid @RequestBody PermissionGroup permissionGroup) {
        permissionService.updateGroup(permissionGroup);
        return ResponseResult.success();
    }

    @NeedToken
    @GetMapping("/groups")
    public ResponseResult getGroups(@RequestParam("type") int type) {
        List<PermissionGroup> permissionGroupList = permissionService.getGroups(type);
        HashMap<String, List<PermissionGroup>> res = new HashMap<>();
        res.put("groups", permissionGroupList);
        return ResponseResult.success(res);
    }

    @NeedToken
    @DeleteMapping("/groups/{groupId}")
    public ResponseResult deleteGroup(@PathVariable Long groupId) {
        permissionService.deleteGroup(groupId);
        return ResponseResult.success();
    }

    @NeedToken
    @DeleteMapping("/{id}")
    public ResponseResult deletePermission(@PathVariable Long id) {
        boolean succeed = permissionService.deletePermission(id);
        if (succeed) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error("请检查权限是否被角色使用中");
        }
    }
}
