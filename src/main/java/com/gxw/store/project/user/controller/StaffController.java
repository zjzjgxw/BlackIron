package com.gxw.store.project.user.controller;

import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.user.dto.StaffDepartmentRel;
import com.gxw.store.project.user.dto.StaffRoleRel;
import com.gxw.store.project.user.entity.business.Staff;
import com.gxw.store.project.user.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Staff staff){
        Long id = staffService.create(staff);
        HashMap<String,Long> res= new HashMap<>();
        res.put("id",id);
        return ResponseResult.success(res);
    }

    @GetMapping("/current")
    public ResponseResult currentStaff()
    {
        Staff staff = staffService.getStaff(SessionUtils.getUserId());
        HashMap<String, Staff> res= new HashMap<>();
        res.put("staff", staff);
        return ResponseResult.success(res);
    }

    @GetMapping("/{id}")
    public ResponseResult getStaff(@PathVariable Long id){
        Staff staff = staffService.getStaff(id);
        HashMap<String, Staff> res= new HashMap<>();
        res.put("staff", staff);
        return ResponseResult.success(res);
    }

    @GetMapping("")
    public ResponseResult getStaffByAccount(@RequestParam("account") String account){
        Staff staff = staffService.getStaffByAccount(account);
        HashMap<String, Staff> res= new HashMap<>();
        res.put("staff", staff);
        return ResponseResult.success(res);
    }

    @PostMapping("/departments")
    public ResponseResult addDepartment(@Validated @RequestBody Set<StaffDepartmentRel> staffDepartmentRelSet){
        if(staffDepartmentRelSet == null || staffDepartmentRelSet.size() == 0){
            return ResponseResult.error();
        }
        staffService.addDepartments(staffDepartmentRelSet);
        return ResponseResult.success();
    }

    @PostMapping("/roles")
    public ResponseResult addRoles(@RequestBody Set<StaffRoleRel> staffRoleRelSet){
        if(staffRoleRelSet == null || staffRoleRelSet.size() == 0){
            return ResponseResult.error();
        }
        staffService.addRoles(staffRoleRelSet);
        return ResponseResult.success();
    }

    @DeleteMapping("/departments")
    public ResponseResult deleteDepartmentRel(@RequestBody Set<StaffDepartmentRel> staffDepartmentRelSet){
        if(staffDepartmentRelSet == null || staffDepartmentRelSet.size() == 0){
            return ResponseResult.error();
        }
        staffService.deleteDepartments(staffDepartmentRelSet);
        return ResponseResult.success();
    }

    @DeleteMapping("/roles")
    public ResponseResult deleteRoles(@RequestBody Set<StaffRoleRel> staffRoleRelSet){
        if(staffRoleRelSet == null || staffRoleRelSet.size() == 0){
            return ResponseResult.error();
        }
        staffService.deleteRoles(staffRoleRelSet);
        return ResponseResult.success();
    }
}
