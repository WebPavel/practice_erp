package cn.com.zv2.invoice.order.dao.impl;

import cn.com.zv2.invoice.order.dao.OrderDao;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.entity.OrderQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    public static final long lastMillisOfDay = 24*60*60*1000 - 1;

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        OrderQueryModel orderQueryModel = (OrderQueryModel) baseQueryModel;
        if (orderQueryModel.getGmtCreate() != null) {
            detachedCriteria.add(Restrictions.ge("gmtCreate", orderQueryModel.getGmtCreate()));
        }
        if (orderQueryModel.getToGmtCreate() != null) {
            detachedCriteria.add(Restrictions.le("gmtCreate", orderQueryModel.getToGmtCreate() + lastMillisOfDay));
        }
        if (orderQueryModel.getType() != null && orderQueryModel.getType() != -1) {
            detachedCriteria.add(Restrictions.eq("type", orderQueryModel.getType()));
        }
        if (orderQueryModel.getStatus() != null && orderQueryModel.getStatus() != -1) {
            detachedCriteria.add(Restrictions.eq("status", orderQueryModel.getStatus()));
        }
        if (orderQueryModel.getAmount() != null && orderQueryModel.getAmount() > 0) {
            detachedCriteria.add(Restrictions.ge("amount", orderQueryModel.getAmount()));
        }
        if (orderQueryModel.getToAmount() != null && orderQueryModel.getToAmount() > 0) {
            detachedCriteria.add(Restrictions.le("amount", orderQueryModel.getToAmount()));
        }
        if (orderQueryModel.getTotal() != null && orderQueryModel.getTotal() > 0) {
            detachedCriteria.add(Restrictions.ge("total", orderQueryModel.getTotal()));
        }
        if (orderQueryModel.getToTotal() != null && orderQueryModel.getToTotal() > 0) {
            detachedCriteria.add(Restrictions.le("total", orderQueryModel.getToTotal()));
        }
        if (orderQueryModel.getApplicant() != null &&
                orderQueryModel.getApplicant().getName() != null &&
                orderQueryModel.getApplicant().getName().trim().length() > 0) {
            detachedCriteria.createAlias("applicant", "applicant");
            detachedCriteria.add(Restrictions.like("applicant.name", "%" + orderQueryModel.getApplicant().getName().trim() + "%"));
        }
    }

}