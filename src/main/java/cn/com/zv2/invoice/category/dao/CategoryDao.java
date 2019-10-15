package cn.com.zv2.invoice.category.dao;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.util.base.BaseDao;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {

    List<Category> listBySupplierId(Long supplierId);

}
