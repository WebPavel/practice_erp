package cn.com.zv2.invoice.order.dao.impl;

import cn.com.zv2.invoice.order.dao.OrderDao;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.entity.OrderQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        OrderQueryModel orderQueryModel = (OrderQueryModel) baseQueryModel;

    }
}
