package cn.com.zv2.invoice.operationdetail.web;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.invoice.operationdetail.entity.OperationDetail;
import cn.com.zv2.invoice.operationdetail.entity.OperationDetailQueryModel;
import cn.com.zv2.invoice.operationdetail.service.OperationDetailService;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.warehouse.entity.Warehouse;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class OperationDetailAction extends BaseAction {

    public String warehouseName;
    public String operatorName;
    public String productName;
    public OperationDetail operationDetail = new OperationDetail();
    public OperationDetailQueryModel operationDetailQueryModel = new OperationDetailQueryModel();
    private OperationDetailService operationDetailService;

    public void setOperationDetailService(OperationDetailService operationDetailService) {
        this.operationDetailService = operationDetailService;
    }

    public String list() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseName);
        operationDetailQueryModel.setWarehouse(warehouse);
        Employee operator = new Employee();
        operator.setName(operatorName);
        operationDetailQueryModel.setOperator(operator);
        Product product = new Product();
        product.setName(productName);
        operationDetailQueryModel.setProduct(product);
        setTotalRow(operationDetailService.count(operationDetailQueryModel));
        List<OperationDetail> operationDetailList = operationDetailService.list(operationDetailQueryModel, pageNum, pageSize);
        put("operationDetailList", operationDetailList);
        return LIST;
    }

    public String edit() {
        if (operationDetail.getId() != null) {
            operationDetail = operationDetailService.get(operationDetail.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (operationDetail.getId() == null) {
            operationDetailService.save(operationDetail);
        } else {
            operationDetailService.update(operationDetail);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        operationDetailService.delete(operationDetail);
        return REDIRECT_LIST;
    }

}
