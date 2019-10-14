package cn.com.zv2.invoice.category.dao.impl;

import cn.com.zv2.invoice.category.dao.CategoryDao;
import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.invoice.category.entity.CategoryQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

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
}
