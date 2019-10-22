package cn.com.zv2.auth.employee.service.impl;

import cn.com.zv2.auth.department.entity.Department;
import cn.com.zv2.auth.employee.dao.EmployeeDao;
import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.employee.service.EmployeeService;
import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.exception.ApplicationException;
import cn.com.zv2.util.format.MD5Utils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    private static final int ENCRYPT_PASSWORD_LENGTH = 1 << 5;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void save(Employee employee) {
    }

    @Override
    public void update(Employee employee) {
    }

    @Override
    public void delete(Employee employee) {
        Employee currEmployee = employeeDao.get(employee.getId());
        if (currEmployee == null) {
            throw new ApplicationException("can't find item[id=" + employee.getId() + "]");
        }
        employeeDao.delete(currEmployee);
    }

    @Override
    public Employee get(Serializable id) {
        return employeeDao.get(id);
    }

    @Override
    public List<Employee> list() {
        return employeeDao.list();
    }

    @Override
    public List<Employee> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return employeeDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return employeeDao.count(baseQueryModel);
    }

    @Override
    public Employee login(String username, String password, String ip) {
        if (password == null) {
            throw new IllegalArgumentException("password must not be null");
        }
        if (password != null && password.length() != ENCRYPT_PASSWORD_LENGTH) {
            password = MD5Utils.md5(password);
        }
        Employee employee = employeeDao.getByUsernameAndPassword(username, password);
        if (employee != null) {
            employee.setLastLoginIP(ip);
            employee.setGmtLastLogin(System.currentTimeMillis());
            employee.setLoginCount(employee.getLoginCount() + 1);
        }
        return employee;
    }

    @Override
    public boolean changePwd(String username, String password, String newPassword) {
        if (password != null && password.length() != ENCRYPT_PASSWORD_LENGTH) {
            password = MD5Utils.md5(password);
        }
        if (newPassword != null && newPassword.length() != ENCRYPT_PASSWORD_LENGTH) {
            newPassword = MD5Utils.md5(newPassword);
        }
        return employeeDao.updatePwdByUsernameAndPwd(username, password, newPassword);
    }

    @Override
    public void save(Employee employee, Long departmentId, Long[] roleIds) {
        Department department = new Department();
        department.setId(departmentId);
        employee.setDepartment(department);
        Set<Role> roles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);
            roles.add(role);
        }
        employee.setRoles(roles);
        // check username
        if (employee.getUsername() == null || employee.getUsername().trim().length() == 0) {
            throw new ApplicationException("INFO_EMPLOYEE_USERNAME_IS_EMPTY");
        }
        // encrypt password
        String password = employee.getPassword();
        if (password != null && password.length() != ENCRYPT_PASSWORD_LENGTH) {
            employee.setPassword(MD5Utils.md5(password));
        }
        employee.setLastLoginIP("-");
        employee.setGmtLastLogin(System.currentTimeMillis());
        employee.setLoginCount(0);
        employeeDao.save(employee);
    }

    @Override
    public void update(Employee employee, Long departmentId, Long[] roleIds) {
        // 快照更新
        Employee employeeSnapshot = employeeDao.get(employee.getId());
        employeeSnapshot.setName(employee.getName());
        employeeSnapshot.setEmail(employee.getEmail());
        employeeSnapshot.setTelephone(employee.getTelephone());
        employeeSnapshot.setGender(employee.getGender());
        employeeSnapshot.setBirthday(employee.getBirthday());
        employeeSnapshot.setAddress(employee.getAddress());
        employeeSnapshot.setDepartment(employee.getDepartment());

        Department department = new Department();
        department.setId(departmentId);
        employeeSnapshot.setDepartment(department);
        Set<Role> roles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);
            roles.add(role);
        }
        employeeSnapshot.setRoles(roles);
    }

    @Override
    public List<Employee> listByDepartment(Long departmentId) {
        return employeeDao.listByDepartmentId(departmentId);
    }

}
