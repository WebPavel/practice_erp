package cn.com.zv2.invoice.product.dao;

import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.util.base.BaseDao;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    List<Product> listByCategoryId(Long categoryId);

}
