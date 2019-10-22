package cn.com.zv2.invoice.order.entity;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.util.format.DateUtils;
import cn.com.zv2.util.format.FormatUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lb
 * @date 2019/10/16 3:53
 */
public class Order {

    public static final Integer ORDER_TYPE_OF_BUY = 1;
    public static final Integer ORDER_TYPE_OF_SELL = 2;
    public static final Integer ORDER_TYPE_OF_BUY_REFUND = 3;
    public static final Integer ORDER_TYPE_OF_SELL_REFUND = 4;
    public static final String ORDER_TYPE_OF_BUY_VIEW = "采购";
    public static final String ORDER_TYPE_OF_SELL_VIEW = "销售";
    public static final String ORDER_TYPE_OF_BUY_REFUND_VIEW = "采购退货";
    public static final String ORDER_TYPE_OF_SELL_REFUND_VIEW = "销售退货";


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

    public static final Integer ORDER_STATUS_OF_SELL_UNAUDITED = 211;
    public static final Integer ORDER_STATUS_OF_SELL_APPROVE = 221;
    public static final String ORDER_STATUS_OF_SELL_UNAUDITED_VIEW = "未审核";
    public static final String ORDER_STATUS_OF_SELL_APPROVE_VIEW = "通过";

    public static final Map<Integer, String> typeMap = new HashMap<>();
    public static final Map<Integer, String> buyStatusMap = new LinkedHashMap<>();
    public static final Map<Integer, String> sellStatusMap = new LinkedHashMap<>();
    private static final Map<Integer, String> statusMap = new HashMap<>();
    static {
        typeMap.put(ORDER_TYPE_OF_BUY, ORDER_TYPE_OF_BUY_VIEW);
        typeMap.put(ORDER_TYPE_OF_SELL, ORDER_TYPE_OF_SELL_VIEW);
        typeMap.put(ORDER_TYPE_OF_BUY_REFUND, ORDER_TYPE_OF_BUY_REFUND_VIEW);
        typeMap.put(ORDER_TYPE_OF_SELL_REFUND, ORDER_TYPE_OF_SELL_REFUND_VIEW);


        buyStatusMap.put(ORDER_STATUS_OF_BUY_UNAUDITED, ORDER_STATUS_OF_BUY_UNAUDITED_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_APPROVE, ORDER_STATUS_OF_BUY_APPROVE_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_REJECT, ORDER_STATUS_OF_BUY_REJECT_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_PROCUREMENT, ORDER_STATUS_OF_BUY_PROCUREMENT_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_WAREHOUSING, ORDER_STATUS_OF_BUY_WAREHOUSING_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_STATEMENT, ORDER_STATUS_OF_BUY_STATEMENT_VIEW);

        sellStatusMap.put(ORDER_STATUS_OF_SELL_UNAUDITED, ORDER_STATUS_OF_SELL_UNAUDITED_VIEW);
        sellStatusMap.put(ORDER_STATUS_OF_SELL_APPROVE, ORDER_STATUS_OF_SELL_APPROVE_VIEW);

        statusMap.putAll(buyStatusMap);
        statusMap.putAll(sellStatusMap);
    }

    private Long id;

    /** 订单流水号 */
    private String sn;
    /** 创建时间 */
    private Long gmtCreate;
    /** 审核时间 */
    private Long gmtAudit;
    /** 入库时间 */
    private Long gmtWarehousing;
    private Integer type;
    private Integer status;
    private Integer amount;
    private Double total;
    /** 申请人 */
    private Employee applicant;
    /** 审核人 */
    private Employee auditor;
    /** 跟单员 */
    private Employee merchandiser;
    /** 供应商 */
    private Supplier supplier;
    private Set<OrderDetail> orderDetails;

    /** 创建时间(视图值) */
    private String gmtCreateView;
    /** 审核时间(视图值) */
    private String gmtAuditView;
    /** 入库时间(视图值) */
    private String gmtWarehousingView;
    private String typeView;
    private String statusView;
    private String totalView;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
        if (gmtCreate != null) {
            this.gmtCreateView = DateUtils.formatDateTime(gmtCreate);
        }
    }

    public Long getGmtAudit() {
        return gmtAudit;
    }

    public void setGmtAudit(Long gmtAudit) {
        this.gmtAudit = gmtAudit;
        if (gmtAudit != null) {
            this.gmtAuditView = DateUtils.formatDateTime(gmtAudit);
        }
    }

    public Long getGmtWarehousing() {
        return gmtWarehousing;
    }

    public void setGmtWarehousing(Long gmtWarehousing) {
        this.gmtWarehousing = gmtWarehousing;
        if (gmtWarehousing != null) {
            this.gmtWarehousingView = DateUtils.formatDateTime(gmtWarehousing);
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        this.typeView = typeMap.get(type);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        this.statusView = statusMap.get(status);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
        if (total != null) {
            this.totalView = FormatUtils.formatCurrency(total);
        }
    }

    public Employee getApplicant() {
        return applicant;
    }

    public void setApplicant(Employee applicant) {
        this.applicant = applicant;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getMerchandiser() {
        return merchandiser;
    }

    public void setMerchandiser(Employee merchandiser) {
        this.merchandiser = merchandiser;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setGmtCreateView(String gmtCreateView) {
        this.gmtCreateView = gmtCreateView;
        this.gmtCreate = DateUtils.parse(gmtCreateView);
    }

    public String getGmtCreateView() {
        return gmtCreateView;
    }

    public void setGmtAuditView(String gmtAuditView) {
        this.gmtAuditView = gmtAuditView;
        this.gmtAudit = DateUtils.parse(gmtAuditView);
    }

    public String getGmtAuditView() {
        return gmtAuditView;
    }

    public String getGmtWarehousingView() {
        return gmtWarehousingView;
    }

    public String getTypeView() {
        return typeView;
    }

    public String getStatusView() {
        return statusView;
    }

    public String getTotalView() {
        return totalView;
    }

}
