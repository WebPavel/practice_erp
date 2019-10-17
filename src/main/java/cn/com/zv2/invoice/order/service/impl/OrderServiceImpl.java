package cn.com.zv2.invoice.order.service.impl;

import cn.com.zv2.invoice.order.dao.OrderDao;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.service.OrderService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public Order get(Serializable id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> list() {
        return orderDao.list();
    }

    @Override
    public List<Order> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return orderDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return orderDao.count(baseQueryModel);
    }

}
