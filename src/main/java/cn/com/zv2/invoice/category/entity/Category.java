package cn.com.zv2.invoice.category.entity;

import cn.com.zv2.invoice.supplier.entity.Supplier;

/**
 * @author lb
 * @date 2019/10/14 1:23
 */
public class Category {

    private Long id;
    private String name;
    private Supplier supplier;

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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
