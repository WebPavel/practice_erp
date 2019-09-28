package cn.com.zv2.auth.employee.service;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmployeeService extends BaseService<Employee> {

    /**
     * 根据用户名密码登录
     * @param username 用户名
     * @param password 密码
     * @return 登录用户信息。如果返回null，表示登录失败。
     */
    Employee login(String username, String password);

}
