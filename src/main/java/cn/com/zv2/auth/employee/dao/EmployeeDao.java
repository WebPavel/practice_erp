package cn.com.zv2.auth.employee.dao;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.util.base.BaseDao;

public interface EmployeeDao extends BaseDao<Employee> {

    Employee getByUsernameAndPassword(String username, String password);

}
