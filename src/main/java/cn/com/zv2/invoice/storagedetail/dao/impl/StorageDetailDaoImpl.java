package cn.com.zv2.invoice.storagedetail.dao.impl;

import cn.com.zv2.invoice.storagedetail.dao.StorageDetailDao;
import cn.com.zv2.invoice.storagedetail.entity.StorageDetail;
import cn.com.zv2.invoice.storagedetail.entity.StorageDetailQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StorageDetailDaoImpl extends BaseDaoImpl<StorageDetail> implements StorageDetailDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        StorageDetailQueryModel storageDetailQueryModel = (StorageDetailQueryModel) baseQueryModel;
        detachedCriteria.createAlias("warehouse", "warehouse");
        if (storageDetailQueryModel.getWarehouse() != null &&
                storageDetailQueryModel.getWarehouse().getName() != null &&
                storageDetailQueryModel.getWarehouse().getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("warehouse.name", "%" + storageDetailQueryModel.getWarehouse().getName() + "%"));
        }
        if (storageDetailQueryModel.getWarehouse() != null &&
                storageDetailQueryModel.getWarehouse().getKeeper() != null &&
                storageDetailQueryModel.getWarehouse().getKeeper().getName() != null &&
                storageDetailQueryModel.getWarehouse().getKeeper().getName().trim().length() > 0) {
            detachedCriteria.createAlias("warehouse.keeper", "keeper");
            detachedCriteria.add(Restrictions.like("keeper.name", "%" + storageDetailQueryModel.getWarehouse().getKeeper().getName() + "%"));
        }
        if (storageDetailQueryModel.getProduct() != null &&
                storageDetailQueryModel.getProduct().getName() != null &&
                storageDetailQueryModel.getProduct().getName().trim().length() > 0) {
            detachedCriteria.createAlias("product", "product");
            detachedCriteria.add(Restrictions.like("product.name", "%" + storageDetailQueryModel.getProduct().getName() + "%"));
        }
    }

    @Override
    public StorageDetail getByWarehouseAndProduct(Long warehouseId, Long productId) {
        String hql = "from StorageDetail where warehouseId = ? and productId = ?";
        List<StorageDetail> list = this.getHibernateTemplate().find(hql, new Object[]{warehouseId, productId});
        return list.size() > 0 ? list.get(0) : null;
    }
}
