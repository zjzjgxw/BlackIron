package com.gxw.store.project.user.dto;


import javax.validation.constraints.NotNull;
import java.util.Objects;


public class StaffDepartmentRel {
    @NotNull
    private  Long staffId;
    @NotNull
    private Long departmentId;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffDepartmentRel that = (StaffDepartmentRel) o;
        return Objects.equals(staffId, that.staffId) &&
                Objects.equals(departmentId, that.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, departmentId);
    }
}
