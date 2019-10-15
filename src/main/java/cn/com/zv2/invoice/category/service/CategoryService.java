package cn.com.zv2.invoice.category.service;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CategoryService extends BaseService<Category> {

    List<Category> listBySupplier(Long supplierId);

}
