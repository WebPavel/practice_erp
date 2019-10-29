package cn.com.zv2.invoice.product.entity;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.util.format.FormatUtils;

/**
 * @author lb
 * @date 2019/10/15 3:21
 */
public class Product {

    private Long id;
    private String name;
    private String origin;
    private String producer;
    private String unit;
    private Double bid;
    private Double price;
    private Integer popularity;
    private Integer ula;
    private Integer lla;
    private Category category;

    private String bidView;
    private String priceView;

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
        this.bidView = FormatUtils.formatCurrency(bid);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.priceView = FormatUtils.formatCurrency(price);
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getUla() {
        return ula;
    }

    public void setUla(Integer ula) {
        this.ula = ula;
    }

    public Integer getLla() {
        return lla;
    }

    public void setLla(Integer lla) {
        this.lla = lla;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBidView() {
        return bidView;
    }

    public String getPriceView() {
        return priceView;
    }

}
