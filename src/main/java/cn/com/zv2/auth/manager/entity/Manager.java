package cn.com.zv2.auth.manager.entity;

import cn.com.zv2.core.dao.annotation.Like;

/**
 * @author lb
 * @date 2019/9/26 2:38
 */
public class Manager {
    private Long id;
    @Like
    private String firstname;
    @Like
    private String lastname;
    @Like
    private String phone;
    @Like
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
