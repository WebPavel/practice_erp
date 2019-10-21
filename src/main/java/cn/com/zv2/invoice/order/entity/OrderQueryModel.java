package cn.com.zv2.invoice.order.entity;

import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.format.DateUtils;

public class OrderQueryModel extends Order implements BaseQueryModel {

    private Long toGmtCreate;
    private Integer toAmount;
    private Double toTotal;
    private String toGmtCreateView;

    public Long getToGmtCreate() {
        return toGmtCreate;
    }

    public void setToGmtCreate(Long toGmtCreate) {
        this.toGmtCreate = toGmtCreate;
        this.toGmtCreateView = DateUtils.formatDateTime(toGmtCreate);
    }

    public Integer getToAmount() {
        return toAmount;
    }

    public void setToAmount(Integer toAmount) {
        this.toAmount = toAmount;
    }

    public Double getToTotal() {
        return toTotal;
    }

    public void setToTotal(Double toTotal) {
        this.toTotal = toTotal;
    }

    public String getToGmtCreateView() {
        return toGmtCreateView;
    }

    public void setToGmtCreateView(String toGmtCreateView) {
        this.toGmtCreateView = toGmtCreateView;
        this.toGmtCreate = DateUtils.parse(toGmtCreateView);
    }

}
