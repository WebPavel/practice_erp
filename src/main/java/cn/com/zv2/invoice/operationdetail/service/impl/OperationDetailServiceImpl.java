package cn.com.zv2.invoice.operationdetail.service.impl;

import cn.com.zv2.invoice.operationdetail.dao.OperationDetailDao;
import cn.com.zv2.invoice.operationdetail.entity.OperationDetail;
import cn.com.zv2.invoice.operationdetail.service.OperationDetailService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class OperationDetailServiceImpl implements OperationDetailService {

    private OperationDetailDao operationDetailDao;

    public void setOperationDetailDao(OperationDetailDao operationDetailDao) {
        this.operationDetailDao = operationDetailDao;
    }

    @Override
    public void save(OperationDetail operationDetail) {
        operationDetailDao.save(operationDetail);
    }

    @Override
    public void update(OperationDetail operationDetail) {
        operationDetailDao.update(operationDetail);
    }

    @Override
    public void delete(OperationDetail operationDetail) {
        operationDetailDao.delete(operationDetail);
    }

    @Override
    public OperationDetail get(Serializable id) {
        return operationDetailDao.get(id);
    }

    @Override
    public List<OperationDetail> list() {
        return operationDetailDao.list();
    }

    @Override
    public List<OperationDetail> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return operationDetailDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return operationDetailDao.count(baseQueryModel);
    }

}
