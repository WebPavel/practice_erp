package cn.com.zv2.invoice.bill.entity;

import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.format.DateUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class BillQueryModel implements BaseQueryModel {

    /** 未审核 */
    public static final Integer ORDER_STATUS_OF_BUY_UNAUDITED = 111;
    public static final Integer ORDER_STATUS_OF_BUY_APPROVE = 121;
    public static final Integer ORDER_STATUS_OF_BUY_REJECT = 120;
    public static final Integer ORDER_STATUS_OF_BUY_PROCUREMENT = 131;
    public static final Integer ORDER_STATUS_OF_BUY_WAREHOUSING = 141;
    public static final Integer ORDER_STATUS_OF_BUY_STATEMENT = 199;
    public static final String ORDER_STATUS_OF_BUY_UNAUDITED_VIEW = "未审核";
    public static final String ORDER_STATUS_OF_BUY_APPROVE_VIEW = "通过";
    public static final String ORDER_STATUS_OF_BUY_REJECT_VIEW = "驳回";
    public static final String ORDER_STATUS_OF_BUY_PROCUREMENT_VIEW = "采购中";
    public static final String ORDER_STATUS_OF_BUY_WAREHOUSING_VIEW = "入库中";
    public static final String ORDER_STATUS_OF_BUY_STATEMENT_VIEW = "结单";
    public static final Map<Integer, String> buyStatusMap = new LinkedHashMap<>();
    static {
        buyStatusMap.put(ORDER_STATUS_OF_BUY_UNAUDITED, ORDER_STATUS_OF_BUY_UNAUDITED_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_APPROVE, ORDER_STATUS_OF_BUY_APPROVE_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_REJECT, ORDER_STATUS_OF_BUY_REJECT_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_PROCUREMENT, ORDER_STATUS_OF_BUY_PROCUREMENT_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_WAREHOUSING, ORDER_STATUS_OF_BUY_WAREHOUSING_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_STATEMENT, ORDER_STATUS_OF_BUY_STATEMENT_VIEW);
    }

    private Integer orderStatus;
    private Long supplierId;
    private Long productId;
    private Long startTime;
    private Long endTime;

    private String orderStatusView;
    private String startTimeView;
    private String endTimeView;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        this.orderStatusView = buyStatusMap.get(orderStatus);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        this.startTimeView = DateUtils.formatDate(startTime);
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        this.endTimeView = DateUtils.formatDate(endTime);
    }

    public String getOrderStatusView() {
        return orderStatusView;
    }

    public String getStartTimeView() {
        return startTimeView;
    }

    public void setStartTimeView(String startTimeView) {
        this.startTimeView = startTimeView;
        this.startTime = DateUtils.parse(startTimeView);
    }

    public String getEndTimeView() {
        return endTimeView;
    }

    public void setEndTimeView(String endTimeView) {
        this.endTimeView = endTimeView;
        this.endTime = DateUtils.parse(endTimeView);
    }
}
