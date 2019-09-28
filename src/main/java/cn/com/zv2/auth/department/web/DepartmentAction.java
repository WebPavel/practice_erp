package cn.com.zv2.auth.department.web;

import cn.com.zv2.auth.department.entity.Department;
import cn.com.zv2.auth.department.entity.DepartmentQueryModel;
import cn.com.zv2.auth.department.service.DepartmentService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

/**
 * @author lb
 * @date 2019/9/19 2:19
 */
public class DepartmentAction extends BaseAction {

    public Department department = new Department();
    public DepartmentQueryModel departmentQueryModel = new DepartmentQueryModel();
    private DepartmentService departmentService;

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public String list() {
        setTotalRow(departmentService.count(departmentQueryModel));
        List<Department> departmentList = departmentService.list(departmentQueryModel, pageNum, pageSize);
        put("departmentList", departmentList);
        return LIST;
    }

    public String edit() {
        if (department.getId() != null) {
            department = departmentService.get(department.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        System.out.println(department.getName() + "-" + department.getTelephone());
        if (department.getId() == null) {
            departmentService.save(department);
        } else {
            departmentService.update(department);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        departmentService.delete(department);
        return REDIRECT_LIST;
    }
}
