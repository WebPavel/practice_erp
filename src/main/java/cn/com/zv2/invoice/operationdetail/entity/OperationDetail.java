package cn.com.zv2.invoice.operationdetail.entity;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.warehouse.entity.Warehouse;
import cn.com.zv2.util.format.DateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lb
 * @date 2019/10/23 23:52
 */
public class OperationDetail {

    public static final Integer OPERATIONDETAIL_TYPE_OF_IN = 1;
    public static final Integer OPERATIONDETAIL_TYPE_OF_OUT = 2;
    public static final String OPERATIONDETAIL_TYPE_OF_IN_VIEW = "入库";
    public static final String OPERATIONDETAIL_TYPE_OF_OUT_VIEW = "出库";
    public static final Map<Integer, String> typeMap = new HashMap<>();
    static {
        typeMap.put(OPERATIONDETAIL_TYPE_OF_IN, OPERATIONDETAIL_TYPE_OF_IN_VIEW);
        typeMap.put(OPERATIONDETAIL_TYPE_OF_OUT, OPERATIONDETAIL_TYPE_OF_OUT_VIEW);
    }

    private Long id;
    private Long gmtOperate;
    private Integer quantity;
    private Integer type;
    private Employee operator;
    private Warehouse warehouse;
    private Product product;

    private String gmtOperateView;
    private String typeView;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGmtOperate() {
        return gmtOperate;
    }

    public void setGmtOperate(Long gmtOperate) {
        this.gmtOperate = gmtOperate;
        this.gmtOperateView = DateUtils.formatDateTime(gmtOperate);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        this.typeView = typeMap.get(type);
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
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

    public String getGmtOperateView() {
        return gmtOperateView;
    }

    public void setGmtOperateView(String gmtOperateView) {
        this.gmtOperateView = gmtOperateView;
        this.gmtOperate = DateUtils.parse(gmtOperateView);
    }

    public String getTypeView() {
        return typeView;
    }
}
