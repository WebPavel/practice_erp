package cn.com.zv2.auth.department.entity;

/**
 * @author lb
 * @date 2019/9/19 2:08
 */
public class Department {
    private Long id;
    private String name;
    private String telephone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
