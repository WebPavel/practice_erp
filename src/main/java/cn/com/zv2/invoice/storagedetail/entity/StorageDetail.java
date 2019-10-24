package cn.com.zv2.invoice.storagedetail.entity;

import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.warehouse.entity.Warehouse;

/**
 * @author lb
 * @date 2019/10/23 23:47
 */
public class StorageDetail {
    private Long id;
    private Integer quantity;
    private Warehouse warehouse;
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
