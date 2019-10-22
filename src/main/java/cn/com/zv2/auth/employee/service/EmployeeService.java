package cn.com.zv2.auth.employee.service;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EmployeeService extends BaseService<Employee> {

    /**
     * 根据用户名密码登录
     * @param username 用户名
     * @param password 密码
     * @param ip 登录IP地址
     * @return 登录用户信息。如果返回null，表示登录失败。
     */
    Employee login(String username, String password, String ip);

    /**
     * 修改密码
     * @param username 用户名
     * @param password 旧密码
     * @param newPassword 新密码
     * @return 修改是否成功
     */
    boolean changePwd(String username, String password, String newPassword);

    void save(Employee employee, Long departmentId, Long[] roleIds);

    void update(Employee employee, Long departmentId, Long[] roleIds);

    /**
     * 获取指定部门所有员工信息
     * @param departmentId 部门ID
     * @return
     */
    List<Employee> listByDepartment(Long departmentId);

}
