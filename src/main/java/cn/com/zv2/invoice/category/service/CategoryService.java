package cn.com.zv2.invoice.category.service;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CategoryService extends BaseService<Category> {

    List<Category> listBySupplier(Long supplierId);

    /**
     * 获取指定供应商对应的含有商品的类别列表
     * @param supplierId 供应商ID
     * @return
     */
    List<Category> listNonNullProductBySupplier(Long supplierId);

}
