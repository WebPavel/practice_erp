package cn.com.zv2.invoice.order.service;

import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderService extends BaseService<Order> {

}
