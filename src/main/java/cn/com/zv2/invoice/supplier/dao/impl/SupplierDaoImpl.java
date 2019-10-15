package cn.com.zv2.invoice.supplier.dao.impl;

import cn.com.zv2.invoice.supplier.dao.SupplierDao;
import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.invoice.supplier.entity.SupplierQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SupplierDaoImpl extends BaseDaoImpl<Supplier> implements SupplierDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        SupplierQueryModel supplierQueryModel = (SupplierQueryModel) baseQueryModel;
        if (supplierQueryModel.getName() != null && supplierQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + supplierQueryModel.getName() + "%"));
        }
        if (supplierQueryModel.getTelephone() != null && supplierQueryModel.getTelephone().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("telephone", "%" + supplierQueryModel.getTelephone() + "%"));
        }
        if (supplierQueryModel.getContact() != null && supplierQueryModel.getContact().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("contact", "%" + supplierQueryModel.getContact() + "%"));
        }
        if (supplierQueryModel.getDelivered() != null && supplierQueryModel.getDelivered() != -1) {
            detachedCriteria.add(Restrictions.eq("delivered", supplierQueryModel.getDelivered()));
        }
    }

    @Override
    public List<Supplier> listUnionCategory() {
        String hql = "select distinct supplier from Category category join category.supplier supplier";
        return this.getHibernateTemplate().find(hql);
    }
}
