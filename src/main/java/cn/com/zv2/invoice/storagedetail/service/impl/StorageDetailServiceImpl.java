package cn.com.zv2.invoice.storagedetail.service.impl;

import cn.com.zv2.invoice.storagedetail.dao.StorageDetailDao;
import cn.com.zv2.invoice.storagedetail.entity.StorageDetail;
import cn.com.zv2.invoice.storagedetail.service.StorageDetailService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class StorageDetailServiceImpl implements StorageDetailService {

    private StorageDetailDao storageDetailDao;

    public void setStorageDetailDao(StorageDetailDao storageDetailDao) {
        this.storageDetailDao = storageDetailDao;
    }

    @Override
    public void save(StorageDetail storageDetail) {
        storageDetailDao.save(storageDetail);
    }

    @Override
    public void update(StorageDetail storageDetail) {
        storageDetailDao.update(storageDetail);
    }

    @Override
    public void delete(StorageDetail storageDetail) {
        storageDetailDao.delete(storageDetail);
    }

    @Override
    public StorageDetail get(Serializable id) {
        return storageDetailDao.get(id);
    }

    @Override
    public List<StorageDetail> list() {
        return storageDetailDao.list();
    }

    @Override
    public List<StorageDetail> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return storageDetailDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return storageDetailDao.count(baseQueryModel);
    }

}
