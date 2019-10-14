package cn.com.zv2.invoice.supplier.web;

import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.invoice.supplier.entity.SupplierQueryModel;
import cn.com.zv2.invoice.supplier.service.SupplierService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class SupplierAction extends BaseAction {

    public Supplier supplier = new Supplier();
    public SupplierQueryModel supplierQueryModel = new SupplierQueryModel();
    private SupplierService supplierService;

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public String list() {
        setTotalRow(supplierService.count(supplierQueryModel));
        List<Supplier> supplierList = supplierService.list(supplierQueryModel, pageNum, pageSize);
        put("supplierList", supplierList);
        return LIST;
    }

    public String edit() {
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (supplier.getId() == null) {
            supplierService.save(supplier);
        } else {
            supplierService.update(supplier);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        supplierService.delete(supplier);
        return REDIRECT_LIST;
    }

}
