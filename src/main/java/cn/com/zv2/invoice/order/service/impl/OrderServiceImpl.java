package cn.com.zv2.invoice.order.service.impl;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.invoice.operationdetail.dao.OperationDetailDao;
import cn.com.zv2.invoice.operationdetail.entity.OperationDetail;
import cn.com.zv2.invoice.order.dao.OrderDao;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.entity.OrderQueryModel;
import cn.com.zv2.invoice.order.service.OrderService;
import cn.com.zv2.invoice.orderdetail.dao.OrderDetailDao;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.storagedetail.dao.StorageDetailDao;
import cn.com.zv2.invoice.storagedetail.entity.StorageDetail;
import cn.com.zv2.invoice.warehouse.entity.Warehouse;
import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.exception.ApplicationException;
import cn.com.zv2.util.number.SerialNumberUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private OrderDetailDao orderDetailDao;
    private StorageDetailDao storageDetailDao;
    private OperationDetailDao operationDetailDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    public void setStorageDetailDao(StorageDetailDao storageDetailDao) {
        this.storageDetailDao = storageDetailDao;
    }

    public void setOperationDetailDao(OperationDetailDao operationDetailDao) {
        this.operationDetailDao = operationDetailDao;
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
            orderDetail.setPrice(prices[i]);
            orderDetail.setQuantity(quantities[i]);
            orderDetail.setSurplus(quantities[i]);
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
    public Integer countBuyOrder(OrderQueryModel orderQueryModel) {
        orderQueryModel.setType(Order.ORDER_TYPE_OF_BUY);
        return orderDao.count(orderQueryModel);
    }

    @Override
    public List<Order> listBuyOrder(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize) {
        orderQueryModel.setType(Order.ORDER_TYPE_OF_BUY);
        return orderDao.list(orderQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer countBuyAudit(OrderQueryModel orderQueryModel) {
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

    @Override
    public Integer countTask(OrderQueryModel orderQueryModel) {
        return orderDao.countByStatuses(orderQueryModel, taskStatuses);
    }

    @Override
    public List<Order> listTask(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize) {
        return orderDao.listByStatuses(orderQueryModel, pageNum, pageSize, taskStatuses);
    }

    @Override
    public void assignTask(Long orderId, Employee merchandiser) {
        Order orderSnapshot = orderDao.get(orderId);
        // 逻辑校验
        if (!Order.ORDER_STATUS_OF_BUY_APPROVE.equals(orderSnapshot.getStatus())) {
            throw new ApplicationException("对不起,请不要进行非法操作!");
        }
        orderSnapshot.setMerchandiser(merchandiser);
        orderSnapshot.setStatus(Order.ORDER_STATUS_OF_BUY_PROCUREMENT);
    }

    @Override
    public Integer countMyTask(OrderQueryModel orderQueryModel, Employee employee) {
        orderQueryModel.setMerchandiser(employee);
        return orderDao.count(orderQueryModel);
    }

    @Override
    public List<Order> listMyTask(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize, Employee employee) {
        orderQueryModel.setMerchandiser(employee);
        return orderDao.list(orderQueryModel, pageNum, pageSize);
    }

    @Override
    public void endTask(Long orderId) {
        Order orderSnapshot = orderDao.get(orderId);
        // 逻辑校验
        if (!Order.ORDER_STATUS_OF_BUY_PROCUREMENT.equals(orderSnapshot.getStatus())) {
            throw new ApplicationException("对不起,请不要进行非法操作!");
        }
        orderSnapshot.setStatus(Order.ORDER_STATUS_OF_BUY_WAREHOUSING);
    }

    @Override
    public Integer countWarehousing(OrderQueryModel orderQueryModel) {
        return orderDao.countByStatuses(orderQueryModel, warehousingStatuses);
    }

    @Override
    public List<Order> listWarehousing(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize) {
        return orderDao.listByStatuses(orderQueryModel, pageNum, pageSize, warehousingStatuses);
    }

    @Override
    public OrderDetail inProduct(Long warehouseId, Long orderDetailId, Integer inQuantity, Employee keeper) {
        // 入库流程:1.更新订单明细剩余数量,2.更新库存商品数量,3.记录操作日志
        OrderDetail orderDetailSnapshot = orderDetailDao.get(orderDetailId);
        Order order = orderDetailSnapshot.getOrder();
        // 逻辑校验
        if (!Order.ORDER_STATUS_OF_BUY_WAREHOUSING.equals(order.getStatus())) {
            throw new ApplicationException("对不起,请不要进行非法操作!");
        }
        if (orderDetailSnapshot.getQuantity() < inQuantity) {
            throw new ApplicationException("illegal parameter");
        }
        orderDetailSnapshot.setSurplus(orderDetailSnapshot.getSurplus() - inQuantity);
        Product product = orderDetailSnapshot.getProduct();
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseId);
        StorageDetail storageDetail = storageDetailDao.getByWarehouseAndProduct(warehouseId, product.getId());
        if (storageDetail != null) {
            storageDetail.setQuantity(storageDetail.getQuantity() + inQuantity);
        } else {
            storageDetail = new StorageDetail();
            storageDetail.setWarehouse(warehouse);
            storageDetail.setProduct(product);
            storageDetail.setQuantity(inQuantity);
            storageDetailDao.save(storageDetail);
        }
        OperationDetail operationDetail = new OperationDetail();
        operationDetail.setGmtOperate(System.currentTimeMillis());
        operationDetail.setQuantity(inQuantity);
        operationDetail.setType(OperationDetail.OPERATIONDETAIL_TYPE_OF_IN);
        operationDetail.setOperator(keeper);
        operationDetail.setWarehouse(warehouse);
        operationDetail.setProduct(product);
        operationDetailDao.save(operationDetail);
        // 判断所有订单明细全部入库，修改订单状态
        Integer countSurplus = 0;
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            countSurplus += orderDetail.getSurplus();
        }
        if (countSurplus == 0) {
            order.setStatus(Order.ORDER_STATUS_OF_BUY_STATEMENT);
            order.setGmtWarehousing(System.currentTimeMillis());
        }
        return orderDetailSnapshot;
    }

    private Integer[] buyAuditTypes = new Integer[]{
            Order.ORDER_TYPE_OF_BUY,
            Order.ORDER_TYPE_OF_BUY_REFUND
    };

    private Integer[] taskStatuses = new Integer[]{
            Order.ORDER_STATUS_OF_BUY_APPROVE,
            Order.ORDER_STATUS_OF_BUY_PROCUREMENT,
            Order.ORDER_STATUS_OF_BUY_WAREHOUSING,
            Order.ORDER_STATUS_OF_BUY_STATEMENT
    };

    private Integer[] warehousingStatuses = new Integer[]{
            Order.ORDER_STATUS_OF_BUY_WAREHOUSING
    };
}
