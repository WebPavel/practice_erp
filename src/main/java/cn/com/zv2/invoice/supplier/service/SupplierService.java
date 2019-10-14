package cn.com.zv2.invoice.supplier.service;

import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SupplierService extends BaseService<Supplier> {

}
