package cn.com.zv2.invoice.orderdetail.entity;

import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.util.format.FormatUtils;

/**
 * @author lb
 * @date 2019/10/20 17:40
 */
public class OrderDetail {

    private Long id;
    private Integer quantity;
    private Double price;
    private Integer surplus;
    private Product product;
    private Order order;

    private String priceView;
    private String subtotalView;

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
        this.subtotalView = FormatUtils.formatCurrency(quantity * price);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.priceView = FormatUtils.formatCurrency(price);
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPriceView() {
        return priceView;
    }

    public String getSubtotalView() {
        return subtotalView;
    }

}
