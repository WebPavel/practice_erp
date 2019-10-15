package cn.com.zv2.invoice.product.dao.impl;

import cn.com.zv2.invoice.product.dao.ProductDao;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.product.entity.ProductQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        ProductQueryModel productQueryModel = (ProductQueryModel) baseQueryModel;
        if (productQueryModel.getName() != null && productQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + productQueryModel.getName().trim() + "%"));
        }
        if (productQueryModel.getOrigin() != null && productQueryModel.getOrigin().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("origin", "%" + productQueryModel.getOrigin().trim() + "%"));
        }
        if (productQueryModel.getProducer() != null && productQueryModel.getProducer().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("producer", "%" + productQueryModel.getProducer().trim() + "%"));
        }
        if (productQueryModel.getUnit() != null && productQueryModel.getUnit().trim().length() > 0) {
            detachedCriteria.add(Restrictions.eq("unit", productQueryModel.getUnit().trim()));
        }
        if (productQueryModel.getBid() != null && productQueryModel.getBid() > 0.0) {
            detachedCriteria.add(Restrictions.ge("bid", productQueryModel.getBid()));
        }
        if (productQueryModel.getToBid() != null && productQueryModel.getToBid() > 0.0) {
            detachedCriteria.add(Restrictions.le("bid", productQueryModel.getToBid()));
        }
        if (productQueryModel.getPrice() != null && productQueryModel.getPrice() > 0.0) {
            detachedCriteria.add(Restrictions.ge("price", productQueryModel.getPrice()));
        }
        if (productQueryModel.getToPrice() != null && productQueryModel.getToPrice() > 0.0) {
            detachedCriteria.add(Restrictions.le("price", productQueryModel.getToPrice()));
        }
        // 使用别名解决字段名联查问题
//        if (productQueryModel.getCategoryList() != null && productQueryModel.getCategoryList().size() > 0) {
//            detachedCriteria.add(Restrictions.in("category", productQueryModel.getCategoryList()));
//        }
        if (productQueryModel.getCategory() != null && productQueryModel.getCategory().getSupplier() != null && productQueryModel.getCategory().getSupplier().getId() != null && productQueryModel.getCategory().getSupplier().getId() != -1) {
//            detachedCriteria.createAlias("category", "category");
//            detachedCriteria.createAlias("category.supplier", "supplier");
//            detachedCriteria.add(Restrictions.eq("supplier.id", productQueryModel.getCategory().getSupplier().getId()));
            detachedCriteria.createAlias("category", "category");
            detachedCriteria.add(Restrictions.eq("category.supplier", productQueryModel.getCategory().getSupplier()));
        }
    }
}
