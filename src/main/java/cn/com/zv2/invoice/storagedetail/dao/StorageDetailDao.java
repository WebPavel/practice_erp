package cn.com.zv2.invoice.storagedetail.dao;

import cn.com.zv2.invoice.storagedetail.entity.StorageDetail;
import cn.com.zv2.util.base.BaseDao;

public interface StorageDetailDao extends BaseDao<StorageDetail> {

    StorageDetail getByWarehouseAndProduct(Long warehouseId, Long productId);
}
