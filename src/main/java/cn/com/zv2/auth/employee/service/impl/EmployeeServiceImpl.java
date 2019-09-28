package cn.com.zv2.auth.employee.service.impl;

import cn.com.zv2.auth.employee.dao.EmployeeDao;
import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.employee.service.EmployeeService;
import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.format.MD5Utils;

import java.io.Serializable;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeDao.delete(employee);
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
    public Employee login(String username, String password) {
        if (password == null) {
            throw new IllegalArgumentException("password must not be null");
        }
        password = MD5Utils.md5(password);
        Employee employee = employeeDao.getByUsernameAndPassword(username, password);
        return employee;
    }

}
