package cn.com.zv2.invoice.warehouse.service.impl;

import cn.com.zv2.invoice.warehouse.dao.WarehouseDao;
import cn.com.zv2.invoice.warehouse.entity.Warehouse;
import cn.com.zv2.invoice.warehouse.service.WarehouseService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class WarehouseServiceImpl implements WarehouseService {

    private WarehouseDao warehouseDao;

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    @Override
    public void save(Warehouse warehouse) {
        warehouseDao.save(warehouse);
    }

    @Override
    public void update(Warehouse warehouse) {
        warehouseDao.update(warehouse);
    }

    @Override
    public void delete(Warehouse warehouse) {
        warehouseDao.delete(warehouse);
    }

    @Override
    public Warehouse get(Serializable id) {
        return warehouseDao.get(id);
    }

    @Override
    public List<Warehouse> list() {
        return warehouseDao.list();
    }

    @Override
    public List<Warehouse> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return warehouseDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return warehouseDao.count(baseQueryModel);
    }

}
