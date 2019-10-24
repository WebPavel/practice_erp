package cn.com.zv2.invoice.warehouse.entity;

import cn.com.zv2.auth.employee.entity.Employee;

/**
 * @author lb
 * @date 2019/10/23 19:45
 */
public class Warehouse {
    private Long id;
    private String name;
    private String address;
    private Employee keeper;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getKeeper() {
        return keeper;
    }

    public void setKeeper(Employee keeper) {
        this.keeper = keeper;
    }
}
