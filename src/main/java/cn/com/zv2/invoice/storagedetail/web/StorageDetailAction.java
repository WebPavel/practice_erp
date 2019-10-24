package cn.com.zv2.invoice.storagedetail.web;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.storagedetail.entity.StorageDetail;
import cn.com.zv2.invoice.storagedetail.entity.StorageDetailQueryModel;
import cn.com.zv2.invoice.storagedetail.service.StorageDetailService;
import cn.com.zv2.invoice.warehouse.entity.Warehouse;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class StorageDetailAction extends BaseAction {

    public String warehouseName;
    public String keeperName;
    public String productName;
    public StorageDetail storageDetail = new StorageDetail();
    public StorageDetailQueryModel storageDetailQueryModel = new StorageDetailQueryModel();
    private StorageDetailService storageDetailService;

    public void setStorageDetailService(StorageDetailService storageDetailService) {
        this.storageDetailService = storageDetailService;
    }

    public String list() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseName);
        Employee keeper = new Employee();
        keeper.setName(keeperName);
        warehouse.setKeeper(keeper);
        storageDetailQueryModel.setWarehouse(warehouse);
        Product product = new Product();
        product.setName(productName);
        storageDetailQueryModel.setProduct(product);
        setTotalRow(storageDetailService.count(storageDetailQueryModel));
        List<StorageDetail> storageDetailList = storageDetailService.list(storageDetailQueryModel, pageNum, pageSize);
        put("storageDetailList", storageDetailList);
        return LIST;
    }

    public String edit() {
        if (storageDetail.getId() != null) {
            storageDetail = storageDetailService.get(storageDetail.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (storageDetail.getId() == null) {
            storageDetailService.save(storageDetail);
        } else {
            storageDetailService.update(storageDetail);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        storageDetailService.delete(storageDetail);
        return REDIRECT_LIST;
    }

}
