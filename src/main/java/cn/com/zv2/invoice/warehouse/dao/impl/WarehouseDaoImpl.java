package cn.com.zv2.invoice.warehouse.dao.impl;

import cn.com.zv2.invoice.warehouse.dao.WarehouseDao;
import cn.com.zv2.invoice.warehouse.entity.Warehouse;
import cn.com.zv2.invoice.warehouse.entity.WarehouseQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class WarehouseDaoImpl extends BaseDaoImpl<Warehouse> implements WarehouseDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        WarehouseQueryModel warehouseQueryModel = (WarehouseQueryModel) baseQueryModel;
        if (warehouseQueryModel.getName() != null && warehouseQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + warehouseQueryModel.getName() + "%"));
        }
        if (warehouseQueryModel.getAddress() != null && warehouseQueryModel.getAddress().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("address", "%" + warehouseQueryModel.getAddress() + "%"));
        }
        if (warehouseQueryModel.getKeeper() != null &&
                warehouseQueryModel.getKeeper().getName() != null &&
                warehouseQueryModel.getKeeper().getName().trim().length() > 0) {
            detachedCriteria.createAlias("keeper", "keeper");
            detachedCriteria.add(Restrictions.like("keeper.name", "%" + warehouseQueryModel.getKeeper().getName() + "%"));
        }
    }
}
