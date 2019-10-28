package cn.com.zv2.invoice.bill.dao.impl;

import cn.com.zv2.invoice.bill.dao.BillDao;
import cn.com.zv2.invoice.bill.entity.BillQueryModel;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BillDaoImpl extends HibernateDaoSupport implements BillDao {

    public static final long lastMillisOfDay = 24*60*60*1000 - 1;

    @Override
    public List<Object[]> listBuyBill(BillQueryModel billQueryModel) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderDetail.class);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.groupProperty("product"));
        projectionList.add(Projections.sum("quantity"));
        detachedCriteria.setProjection(projectionList);
        detachedCriteria.createAlias("order", "order");
        if (billQueryModel.getOrderStatus() != null && billQueryModel.getOrderStatus() != -1) {
            detachedCriteria.add(Restrictions.eq("order.status", billQueryModel.getOrderStatus()));
        }
        if (billQueryModel.getSupplierId() != null && billQueryModel.getSupplierId() != -1) {
            detachedCriteria.createAlias("order.supplier", "supplier");
            detachedCriteria.add(Restrictions.eq("supplier.id", billQueryModel.getSupplierId()));
        }
        if (billQueryModel.getStartTime() != null) {
            detachedCriteria.add(Restrictions.ge("order.gmtCreate", billQueryModel.getStartTime()));
        }
        if (billQueryModel.getEndTime() != null) {
            detachedCriteria.add(Restrictions.le("order.gmtWarehousing", billQueryModel.getEndTime() + lastMillisOfDay));
        }
        return this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public List<OrderDetail> listOrderDetailByProduct(BillQueryModel billQueryModel) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderDetail.class);
        if (billQueryModel.getOrderStatus() != null && billQueryModel.getOrderStatus() != -1) {
            detachedCriteria.add(Restrictions.eq("order.status", billQueryModel.getOrderStatus()));
        }
        if (billQueryModel.getProductId() != null && billQueryModel.getProductId() != -1) {
            detachedCriteria.add(Restrictions.eq("product.id", billQueryModel.getProductId()));
        }
        if (billQueryModel.getStartTime() != null) {
            detachedCriteria.add(Restrictions.ge("order.gmtCreate", billQueryModel.getStartTime()));
        }
        if (billQueryModel.getEndTime() != null) {
            detachedCriteria.add(Restrictions.le("order.gmtWarehousing", billQueryModel.getEndTime() + lastMillisOfDay));
        }
        return this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
