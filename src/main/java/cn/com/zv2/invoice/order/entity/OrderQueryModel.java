package cn.com.zv2.invoice.order.entity;

import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.format.DateUtils;

public class OrderQueryModel extends Order implements BaseQueryModel {

    private Long toGmtCreate;
    private Long toGmtAudit;
    private Integer toAmount;
    private Double toTotal;
    private String toGmtCreateView;
    private String toGmtAuditView;

    public Long getToGmtCreate() {
        return toGmtCreate;
    }

    public void setToGmtCreate(Long toGmtCreate) {
        this.toGmtCreate = toGmtCreate;
        this.toGmtCreateView = DateUtils.formatDateTime(toGmtCreate);
    }

    public Long getToGmtAudit() {
        return toGmtAudit;
    }

    public void setToGmtAudit(Long toGmtAudit) {
        this.toGmtAudit = toGmtAudit;
        this.toGmtAuditView = DateUtils.formatDate(toGmtAudit);
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

    public String getToGmtAuditView() {
        return toGmtAuditView;
    }

    public void setToGmtAuditView(String toGmtAuditView) {
        this.toGmtAuditView = toGmtAuditView;
        this.toGmtAudit = DateUtils.parse(toGmtAuditView);
    }

}
