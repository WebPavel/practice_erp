package cn.com.zv2.auth.employee.web;

import cn.com.zv2.auth.department.entity.Department;
import cn.com.zv2.auth.department.service.DepartmentService;
import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.employee.entity.EmployeeQueryModel;
import cn.com.zv2.auth.employee.service.EmployeeService;
import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.auth.resource.service.ResourceService;
import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.auth.role.service.RoleService;
import cn.com.zv2.util.WebUtils;
import cn.com.zv2.util.base.BaseAction;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EmployeeAction extends BaseAction {

    public String newPassword;
    public Long departmentId;
    public Long[] roleIds;
    public Employee employee = new Employee();
    public EmployeeQueryModel employeeQueryModel = new EmployeeQueryModel();
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private RoleService roleService;
    private ResourceService resourceService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public String list() {
        List<Department> departmentList = departmentService.list();
        put("departmentList", departmentList);
        setTotalRow(employeeService.count(employeeQueryModel));
        List<Employee> employeeList = employeeService.list(employeeQueryModel, pageNum, pageSize);
        put("employeeList", employeeList);
        return LIST;
    }

    public String edit() {
        List<Role> roleList = roleService.list();
        put("roleList", roleList);
        List<Department> departmentList = departmentService.list();
        put("departmentList", departmentList);
        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());
            if (employee.getDepartment() != null) {
                departmentId = employee.getDepartment().getId();
            }
            roleIds = new Long[employee.getRoles().size()];
            int i = 0;
            for (Role role : employee.getRoles()) {
                roleIds[i++] = role.getId();
            }
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (employee.getId() == null) {
            employeeService.save(employee, departmentId, roleIds);
        } else {
            employeeService.update(employee, departmentId, roleIds);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        employeeService.delete(employee);
        return REDIRECT_LIST;
    }

    public String login() {
        HttpServletRequest request = getRequest();
        String ip = WebUtils.getIPAddress(request);
        Employee currEmployee = employeeService.login(employee.getUsername(), employee.getPassword(), ip);
        if (currEmployee == null) {
            this.addActionError("登录失败，用户名密码错误！");
            return "loginFail";
        } else {
            List<Resource> resourceListOfEmployee = resourceService.listByEmployee(currEmployee.getId());
            StringBuilder resourceURL = new StringBuilder();
            for (Resource resource : resourceListOfEmployee) {
                resourceURL.append(resource.getUrl());
                resourceURL.append(",");
            }
            currEmployee.setResourceURL(resourceURL.toString());
            putSession(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME, currEmployee);
            return "loginSuccess";
        }
    }

    public String logout() {
        putSession(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME, null);
        return "login";
    }

    public String doChangePwd() {
        return "doChangePwd";
    }

    public String changePwd() {
        boolean success = employeeService.changePwd(getSessionEmployee().getUsername(), employee.getPassword(), newPassword);
        if (success) {
            putSession(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME, null);
            return "login";
        } else {
            this.addActionError("原始密码不正确");
            return "doChangePwd";
        }
    }

    protected Employee getSessionEmployee() {
        return (Employee) getSession(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME);
    }

}
