package cn.com.zv2.invoice.product.service.impl;

import cn.com.zv2.invoice.product.dao.ProductDao;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.product.service.ProductService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    public Product get(Serializable id) {
        return productDao.get(id);
    }

    @Override
    public List<Product> list() {
        return productDao.list();
    }

    @Override
    public List<Product> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return productDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return productDao.count(baseQueryModel);
    }

}
