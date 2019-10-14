package cn.com.zv2.invoice.supplier.service.impl;

import cn.com.zv2.invoice.supplier.dao.SupplierDao;
import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.invoice.supplier.service.SupplierService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    private SupplierDao supplierDao;

    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public void save(Supplier supplier) {
        supplierDao.save(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        supplierDao.update(supplier);
    }

    @Override
    public void delete(Supplier supplier) {
        supplierDao.delete(supplier);
    }

    @Override
    public Supplier get(Serializable id) {
        return supplierDao.get(id);
    }

    @Override
    public List<Supplier> list() {
        return supplierDao.list();
    }

    @Override
    public List<Supplier> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return supplierDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return supplierDao.count(baseQueryModel);
    }

}
