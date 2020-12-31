package com.gxw.store.project.background.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.user.VO.AdminVO;
import com.gxw.store.project.user.dto.AdminRoleRel;
import com.gxw.store.project.user.dto.AdminSearchParams;
import com.gxw.store.project.user.entity.admin.Admin;
import com.gxw.store.project.user.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admins")
public class AdminController extends BaseController {
    @Resource
    private AdminService adminService;

    @NeedToken
    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Admin admin) {
        Long id = adminService.create(admin);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping("/{id}")
    public ResponseResult getAdmin(@PathVariable Long id) {
        Admin admin = adminService.getAdmin(id);
        HashMap<String, Admin> res = new HashMap<>();
        res.put("admin", admin);
        return ResponseResult.success(res);
    }

    @NeedToken
    @GetMapping()
    public ResponseResult getAdmins(@RequestParam(required = false) Long id,
                                    @RequestParam(required = false) String account,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) List<Integer> status ) {
        startPage();
        List<AdminVO> admins  = null;
        AdminSearchParams adminSearchParams = new AdminSearchParams(id,account,email,status);
        admins = adminService.getAdmins(adminSearchParams);
        return ResponseResult.success(getDataTable(admins));
    }

    @NeedToken
    @PutMapping()
    public ResponseResult updateAdmin(@Valid @RequestBody Admin admin) {
        if (adminService.updateAdmin(admin)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @NeedToken
    @DeleteMapping("/{id}")
    public ResponseResult deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseResult.success();
    }

    /**
     * 添加管理员与角色关系
     *
     * @param roleRelSet
     * @return
     */
    @NeedToken
    @PostMapping("/roleRel")
    public ResponseResult addAdminRoles(@RequestBody Set<AdminRoleRel> roleRelSet) {
        if (roleRelSet == null || roleRelSet.size() == 0) {
            return ResponseResult.error();
        }
        adminService.addAdminRoles(roleRelSet);
        return ResponseResult.success();
    }

    /**
     * 删除管理员与角色关系
     *
     * @param roleRelSet
     * @return
     */
    @NeedToken
    @DeleteMapping("/roleRel")
    public ResponseResult deleteAdminRoles(@RequestBody Set<AdminRoleRel> roleRelSet) {
        if (roleRelSet == null || roleRelSet.size() == 0) {
            return ResponseResult.error();
        }
        adminService.deleteAdminRoles(roleRelSet);
        return ResponseResult.success();
    }
}
