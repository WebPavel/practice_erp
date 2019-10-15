package cn.com.zv2.invoice.supplier.service;

import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SupplierService extends BaseService<Supplier> {

    /**
     * 获取含有商品类别的供应商列表
     * @return
     */
    List<Supplier> listUnionCategory();

}
