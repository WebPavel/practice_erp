package cn.com.zv2.invoice.category.dao.impl;

import cn.com.zv2.invoice.category.dao.CategoryDao;
import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.invoice.category.entity.CategoryQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        CategoryQueryModel categoryQueryModel = (CategoryQueryModel) baseQueryModel;
        if (categoryQueryModel.getName() != null && categoryQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + categoryQueryModel.getName() + "%"));
        }
        if (categoryQueryModel.getSupplier() != null && categoryQueryModel.getSupplier().getId() != null && categoryQueryModel.getSupplier().getId() != -1) {
            detachedCriteria.add(Restrictions.eq("supplier", categoryQueryModel.getSupplier()));
        }
    }

    @Override
    public List<Category> listBySupplierId(Long supplierId) {
        String hql = "from Category where supplier.id = ?";
        return this.getHibernateTemplate().find(hql, supplierId);
    }

    @Override
    public List<Category> listNonNullProductBySupplierId(Long supplierId) {
        String hql = "select distinct category from Product product join product.category category where category.supplier.id = ?";
        return this.getHibernateTemplate().find(hql, supplierId);
    }

}
