package cn.com.zv2.auth.employee.entity;

import cn.com.zv2.auth.department.entity.Department;

/**
 * @author lb
 * @date 2019/9/18 1:33
 */
public class Employee {

    public static final String EMPLOYEE_LOGIN_USER_OBJECT_NAME = "loginEmployee";

    private Long id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 真实姓名
    private String name;
    // 电子邮箱
    private String email;
    // 电话号码
    private String telephone;
    // 地址
    private String address;
    // 性别{0:男,1:女}
    private Integer gender;
    // 出生日期
    private Long birthday;
    // 所属部门
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
