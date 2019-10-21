package cn.com.zv2.invoice.orderdetail.service;

import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderDetailService extends BaseService<OrderDetail> {

}
