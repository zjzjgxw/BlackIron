package com.gxw.store.project.background.controller;

import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.user.dto.StaffDepartmentRel;
import com.gxw.store.project.user.dto.StaffRoleRel;
import com.gxw.store.project.user.dto.StaffUpdate;
import com.gxw.store.project.user.entity.business.Staff;
import com.gxw.store.project.user.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/staffs")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody Staff staff) {
        staff.setBusinessId(SessionUtils.getBusinessId());
        Long id = staffService.create(staff);
        HashMap<String, Long> res = new HashMap<>();
        res.put("id", id);
        return ResponseResult.success(res);
    }

    @GetMapping("/current")
    public ResponseResult currentStaff() {
        Staff staff = staffService.getStaff(SessionUtils.getUserId());
        HashMap<String, Staff> res = new HashMap<>();
        res.put("staff", staff);
        return ResponseResult.success(res);
    }

    @GetMapping("/{id}")
    public ResponseResult getStaff(@PathVariable Long id) {
        Staff staff = staffService.getStaff(id);
        HashMap<String, Staff> res = new HashMap<>();
        res.put("staff", staff);
        return ResponseResult.success(res);
    }

    @GetMapping("")
    public ResponseResult getStaffs() {
        startPage();
        List<Staff> staffs = staffService.getStaffs(SessionUtils.getBusinessId());
        return ResponseResult.success(getDataTable(staffs));
    }

    @DeleteMapping("/{id}")
    public ResponseResult  deleteStaff(@PathVariable Long id){
        if(staffService.delete(id,SessionUtils.getBusinessId())){
            return ResponseResult.success();
        }else{
            return ResponseResult.error();
        }
    }

    @PutMapping()
    public ResponseResult updateStaff(@RequestBody StaffUpdate staffUpdate) {
        staffUpdate.setBusinessId(SessionUtils.getBusinessId());
        if (staffService.update(staffUpdate)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @PostMapping("/departments")
    public ResponseResult addDepartment(@Validated @RequestBody Set<StaffDepartmentRel> staffDepartmentRelSet) {
        if (staffDepartmentRelSet == null || staffDepartmentRelSet.size() == 0) {
            return ResponseResult.error();
        }
        staffService.addDepartments(staffDepartmentRelSet);
        return ResponseResult.success();
    }

    @PostMapping("/roles")
    public ResponseResult addRoles(@RequestBody Set<StaffRoleRel> staffRoleRelSet) {
        if (staffRoleRelSet == null || staffRoleRelSet.size() == 0) {
            return ResponseResult.error();
        }
        staffService.addRoles(staffRoleRelSet);
        return ResponseResult.success();
    }

    @DeleteMapping("/departments")
    public ResponseResult deleteDepartmentRel(@RequestBody Set<StaffDepartmentRel> staffDepartmentRelSet) {
        if (staffDepartmentRelSet == null || staffDepartmentRelSet.size() == 0) {
            return ResponseResult.error();
        }
        staffService.deleteDepartments(staffDepartmentRelSet);
        return ResponseResult.success();
    }

    @DeleteMapping("/roles")
    public ResponseResult deleteRoles(@RequestBody Set<StaffRoleRel> staffRoleRelSet) {
        if (staffRoleRelSet == null || staffRoleRelSet.size() == 0) {
            return ResponseResult.error();
        }
        staffService.deleteRoles(staffRoleRelSet);
        return ResponseResult.success();
    }
}
