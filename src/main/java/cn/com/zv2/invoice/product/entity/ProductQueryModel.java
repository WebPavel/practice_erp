package cn.com.zv2.invoice.product.entity;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.util.base.BaseQueryModel;

import java.util.List;

public class ProductQueryModel extends Product implements BaseQueryModel {

    @Deprecated
    private List<Category> categoryList;

    private Double toBid;
    private Double toPrice;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Double getToBid() {
        return toBid;
    }

    public void setToBid(Double toBid) {
        this.toBid = toBid;
    }

    public Double getToPrice() {
        return toPrice;
    }

    public void setToPrice(Double toPrice) {
        this.toPrice = toPrice;
    }

}
