package cn.com.zv2.invoice.order.dao.impl;

import cn.com.zv2.invoice.order.dao.OrderDao;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.entity.OrderQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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
        if (orderQueryModel.getGmtAudit() != null) {
            detachedCriteria.add(Restrictions.ge("gmtAudit", orderQueryModel.getGmtAudit()));
        }
        if (orderQueryModel.getToGmtAudit() != null) {
            detachedCriteria.add(Restrictions.le("gmtAudit", orderQueryModel.getToGmtAudit() + lastMillisOfDay));
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
        if (orderQueryModel.getSupplier() != null &&
                orderQueryModel.getSupplier().getId() != null &&
                orderQueryModel.getSupplier().getId() != -1) {
            detachedCriteria.add(Restrictions.eq("supplier", orderQueryModel.getSupplier()));
        }
        if (orderQueryModel.getSupplier() != null &&
                orderQueryModel.getSupplier().getDelivered() != null &&
                orderQueryModel.getSupplier().getDelivered() != -1) {
            detachedCriteria.createAlias("supplier", "supplier");
            detachedCriteria.add(Restrictions.eq("supplier.delivered", orderQueryModel.getSupplier().getDelivered()));
        }
        if (orderQueryModel.getApplicant() != null &&
                orderQueryModel.getApplicant().getName() != null &&
                orderQueryModel.getApplicant().getName().trim().length() > 0) {
            detachedCriteria.createAlias("applicant", "applicant");
            detachedCriteria.add(Restrictions.like("applicant.name", "%" + orderQueryModel.getApplicant().getName().trim() + "%"));
        }
        if (orderQueryModel.getAuditor() != null &&
                orderQueryModel.getAuditor().getName() != null &&
                orderQueryModel.getAuditor().getName().trim().length() > 0) {
            detachedCriteria.createAlias("auditor", "auditor");
            detachedCriteria.add(Restrictions.like("auditor.name", "%" + orderQueryModel.getAuditor().getName().trim() + "%"));
        }
        if (orderQueryModel.getMerchandiser() != null &&
                orderQueryModel.getMerchandiser().getId() != null &&
                orderQueryModel.getMerchandiser().getId() != -1) {
            detachedCriteria.add(Restrictions.eq("merchandiser", orderQueryModel.getMerchandiser()));
        }
        if (orderQueryModel.getMerchandiser() != null &&
                orderQueryModel.getMerchandiser().getName() != null &&
                orderQueryModel.getMerchandiser().getName().trim().length() > 0) {
            detachedCriteria.createAlias("merchandiser", "merchandiser");
            detachedCriteria.add(Restrictions.like("merchandiser.name", "%" + orderQueryModel.getMerchandiser().getName().trim() + "%"));
        }
    }

    @Override
    public Integer countByTypes(OrderQueryModel orderQueryModel, Integer[] types) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
        detachedCriteria.setProjection(Projections.rowCount());
        enhanceDoQbcByType(detachedCriteria, orderQueryModel, types);
        List<Long> count = this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return count.get(0).intValue();
    }

    @Override
    public List<Order> listByTypes(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize, Integer[] types) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
        enhanceDoQbcByType(detachedCriteria, orderQueryModel, types);
        return this.getHibernateTemplate().findByCriteria(detachedCriteria, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public Integer countByStatuses(OrderQueryModel orderQueryModel, Integer[] statuses) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
        detachedCriteria.setProjection(Projections.rowCount());
        enhanceDoQbcByStatus(detachedCriteria, orderQueryModel, statuses);
        List<Long> count = this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return count.get(0).intValue();
    }

    @Override
    public List<Order> listByStatuses(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize, Integer[] statuses) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
        enhanceDoQbcByStatus(detachedCriteria, orderQueryModel, statuses);
        return this.getHibernateTemplate().findByCriteria(detachedCriteria, (pageNum - 1) * pageSize, pageSize);
    }

    private void enhanceDoQbcByType(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel, Integer[] types) {
        doQbc(detachedCriteria, baseQueryModel);
        detachedCriteria.add(Restrictions.in("type", types));
    }

    private void enhanceDoQbcByStatus(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel, Integer[] statuses) {
        doQbc(detachedCriteria, baseQueryModel);
        detachedCriteria.add(Restrictions.in("status", statuses));
    }

}
