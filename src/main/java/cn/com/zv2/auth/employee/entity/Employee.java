package cn.com.zv2.auth.employee.entity;

import cn.com.zv2.auth.department.entity.Department;
import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.util.format.DateUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lb
 * @date 2019/9/18 1:33
 */
public class Employee {

    public static final String EMPLOYEE_LOGIN_USER_OBJECT_NAME = "loginEmployee";
    public static final Integer EMPLOYEE_GENDER_FEMALE = 0;
    public static final Integer EMPLOYEE_GENDER_MALE = 1;

    public static final String EMPLOYEE_GENDER_FEMALE_VIEW = "女";
    public static final String EMPLOYEE_GENDER_MALE_VIEW = "男";

    public static final Map<Integer, String> genderMap = new HashMap<>(2);

    static {
        genderMap.put(EMPLOYEE_GENDER_FEMALE, EMPLOYEE_GENDER_FEMALE_VIEW);
        genderMap.put(EMPLOYEE_GENDER_MALE, EMPLOYEE_GENDER_MALE_VIEW);
    }

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
    // 性别{0:女,1:男}
    private Integer gender;
    private String genderView;
    // 出生日期
    private Long birthday;
    // 视图值
    private String birthdayView;
    // 最后登录IP
    private String lastLoginIP;
    // 最后登录时间
    private Long gmtLastLogin;
    private String gmtLastLoginView;
    // 登录次数
    private Integer loginCount;
    // 辅助值
    private String resourceURL;
    // 所属部门
    private Department department;
    // 角色列表
    private Set<Role> roles;

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
        this.genderView = genderMap.get(gender);
    }

    public String getGenderView() {
        return genderView;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
        this.birthdayView = DateUtils.formatDate(this.birthday);
    }

    public String getBirthdayView() {
        return birthdayView;
    }

    public void setBirthdayView(String birthdayView) {
        this.birthdayView = birthdayView;
        this.birthday = DateUtils.parse(birthdayView);
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Long getGmtLastLogin() {
        return gmtLastLogin;
    }

    public void setGmtLastLogin(Long gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
        this.gmtLastLoginView = DateUtils.formatDateTime(gmtLastLogin);
    }

    public String getGmtLastLoginView() {
        return gmtLastLoginView;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
