package cn.com.zv2.invoice.orderdetail.dao.impl;

import cn.com.zv2.invoice.orderdetail.dao.OrderDetailDao;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetailQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail> implements OrderDetailDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        OrderDetailQueryModel orderDetailQueryModel = (OrderDetailQueryModel) baseQueryModel;

    }
}
