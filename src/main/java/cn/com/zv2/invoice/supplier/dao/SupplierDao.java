package cn.com.zv2.invoice.supplier.dao;

import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.util.base.BaseDao;

import java.util.List;

public interface SupplierDao extends BaseDao<Supplier> {

    List<Supplier> listUnionCategory();

    List<Supplier> listUnionCategoryAndProduct();

}
