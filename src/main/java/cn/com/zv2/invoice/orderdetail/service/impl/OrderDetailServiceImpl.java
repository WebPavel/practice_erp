package cn.com.zv2.invoice.orderdetail.service.impl;

import cn.com.zv2.invoice.orderdetail.dao.OrderDetailDao;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.invoice.orderdetail.service.OrderDetailService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailDao orderDetailDao;

    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailDao.save(orderDetail);
    }

    @Override
    public void update(OrderDetail orderDetail) {
        orderDetailDao.update(orderDetail);
    }

    @Override
    public void delete(OrderDetail orderDetail) {
        orderDetailDao.delete(orderDetail);
    }

    @Override
    public OrderDetail get(Serializable id) {
        return orderDetailDao.get(id);
    }

    @Override
    public List<OrderDetail> list() {
        return orderDetailDao.list();
    }

    @Override
    public List<OrderDetail> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return orderDetailDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return orderDetailDao.count(baseQueryModel);
    }

}
