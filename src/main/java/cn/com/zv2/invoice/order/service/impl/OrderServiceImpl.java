package cn.com.zv2.invoice.order.service.impl;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.invoice.order.dao.OrderDao;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.entity.OrderQueryModel;
import cn.com.zv2.invoice.order.service.OrderService;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.exception.ApplicationException;
import cn.com.zv2.util.number.SerialNumberUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public Order get(Serializable id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> list() {
        return orderDao.list();
    }

    @Override
    public List<Order> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return orderDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return orderDao.count(baseQueryModel);
    }

    @Override
    public void saveBuyOrder(Order order, Long[] productIds, Integer[] quantities, Double[] prices, Employee applicant) {
        // 设置订单号
        String sn = SerialNumberUtils.generateSN();
        order.setSn(sn);
        order.setGmtCreate(System.currentTimeMillis());
        order.setType(Order.ORDER_TYPE_OF_BUY);
        order.setStatus(Order.ORDER_STATUS_OF_BUY_UNAUDITED);
        order.setApplicant(applicant);
        int amount = 0;
        double total = 0.00;
        Set<OrderDetail> orderDetails = new HashSet<>();
        for (int i = 0; i < productIds.length; i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(quantities[i]);
            orderDetail.setPrice(prices[i]);
            Product product = new Product();
            product.setId(productIds[i]);
            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetails.add(orderDetail);
            amount += quantities[i];
            total += quantities[i] * prices[i];
        }
        order.setOrderDetails(orderDetails);
        order.setAmount(amount);
        order.setTotal(total);
        orderDao.save(order);
    }

    @Override
    public List<Order> listBuyOrder(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize) {
        orderQueryModel.setType(Order.ORDER_TYPE_OF_BUY);
        return orderDao.list(orderQueryModel, pageNum, pageSize);
    }

    @Override
    public int countBuyAudit(OrderQueryModel orderQueryModel) {
        return orderDao.countByTypes(orderQueryModel, buyAuditTypes);
    }

    @Override
    public List<Order> listBuyAudit(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize) {
        return orderDao.listByTypes(orderQueryModel, pageNum, pageSize, buyAuditTypes);
    }

    @Override
    public void buyAuditApprove(Long orderId, Employee auditor) {
        Order orderSnapshot = orderDao.get(orderId);
        // 逻辑校验
        if (!Order.ORDER_STATUS_OF_BUY_UNAUDITED.equals(orderSnapshot.getStatus())) {
            throw new ApplicationException("对不起,请不要进行非法操作!");
        }
        // 快照更新
        orderSnapshot.setStatus(Order.ORDER_STATUS_OF_BUY_APPROVE);
        orderSnapshot.setGmtAudit(System.currentTimeMillis());
        orderSnapshot.setAuditor(auditor);
    }

    @Override
    public void buyAuditReject(Long orderId, Employee auditor) {
        Order orderSnapshot = orderDao.get(orderId);
        // 逻辑校验
        if (!Order.ORDER_STATUS_OF_BUY_UNAUDITED.equals(orderSnapshot.getStatus())) {
            throw new ApplicationException("对不起,请不要进行非法操作!");
        }
        // 快照更新
        orderSnapshot.setStatus(Order.ORDER_STATUS_OF_BUY_REJECT);
        orderSnapshot.setGmtAudit(System.currentTimeMillis());
        orderSnapshot.setAuditor(auditor);
    }

    private Integer[] buyAuditTypes = new Integer[]{
            Order.ORDER_TYPE_OF_BUY,
            Order.ORDER_TYPE_OF_BUY_REFUND
    };

}
