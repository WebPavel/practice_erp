package cn.com.zv2.auth.employee.web;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.employee.entity.EmployeeQueryModel;
import cn.com.zv2.auth.employee.service.EmployeeService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class EmployeeAction extends BaseAction {

    public Employee employee = new Employee();
    public EmployeeQueryModel employeeQueryModel = new EmployeeQueryModel();
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String list() {
        setTotalRow(employeeService.count(employeeQueryModel));
        List<Employee> employeeList = employeeService.list(employeeQueryModel, pageNum, pageSize);
        put("employeeList", employeeList);
        return LIST;
    }

    public String edit() {
        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (employee.getId() == null) {
            employeeService.save(employee);
        } else {
            employeeService.update(employee);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        employeeService.delete(employee);
        return REDIRECT_LIST;
    }

    public String login() {
        Employee loginEmployee = employeeService.login(employee.getUsername(), employee.getPassword());
        if (loginEmployee == null) {
            this.addActionError("登录失败，用户名密码错误！");
            return "loginFail";
        } else {
            putSession(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME, loginEmployee);
            return "loginSuccess";
        }
    }

}
