package cn.com.zv2.auth.employee.entity;

import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.format.DateUtils;

public class EmployeeQueryModel extends Employee implements BaseQueryModel {

    private Long birthday2;
    private String birthday2View;

    public Long getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(Long birthday2) {
        this.birthday2 = birthday2;
        this.birthday2View = DateUtils.formatDate(this.birthday2);
    }

    public String getBirthday2View() {
        return birthday2View;
    }

    public void setBirthday2View(String birthday2View) {
        this.birthday2View = birthday2View;
        this.birthday2 = DateUtils.parse(this.birthday2View);
    }

}
