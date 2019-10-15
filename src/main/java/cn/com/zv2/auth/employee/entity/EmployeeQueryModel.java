package cn.com.zv2.auth.employee.entity;

import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.format.DateUtils;

public class EmployeeQueryModel extends Employee implements BaseQueryModel {

    private Long departmentId;

    private Long toBirthday;
    private String toBirthdayView;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getToBirthday() {
        return toBirthday;
    }

    public void setToBirthday(Long toBirthday) {
        this.toBirthday = toBirthday;
        this.toBirthdayView = DateUtils.formatDate(toBirthday);
    }

    public String getToBirthdayView() {
        return toBirthdayView;
    }

    public void setToBirthdayView(String toBirthdayView) {
        this.toBirthdayView = toBirthdayView;
        this.toBirthday = DateUtils.parse(toBirthdayView);
    }

}
