package cn.com.zv2.invoice.order.dao;

import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.entity.OrderQueryModel;
import cn.com.zv2.util.base.BaseDao;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    Integer countByTypes(OrderQueryModel orderQueryModel, Integer[] types);

    List<Order> listByTypes(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize, Integer[] types);

    Integer countByStatuses(OrderQueryModel orderQueryModel, Integer[] statuses);

    List<Order> listByStatuses(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize, Integer[] statuses);

}
