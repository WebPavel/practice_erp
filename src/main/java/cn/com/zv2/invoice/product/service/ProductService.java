package cn.com.zv2.invoice.product.service;

import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductService extends BaseService<Product> {

}