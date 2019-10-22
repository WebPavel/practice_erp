package cn.com.zv2.invoice.order.service;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.entity.OrderQueryModel;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderService extends BaseService<Order> {

    /**
     * 保存采购订单(注:一个方法尽量不要超过7个参数)
     * @param order 封装了供应商的订单
     * @param productIds 商品的ID数组
     * @param quantities 数量数组
     * @param prices 单价数组
     * @param applicant 申请人
     */
    void saveBuyOrder(Order order, Long[] productIds, Integer[] quantities, Double[] prices, Employee applicant);

    Integer countBuyOrder(OrderQueryModel orderQueryModel);

    List<Order> listBuyOrder(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize);

    Integer countBuyAudit(OrderQueryModel orderQueryModel);

    List<Order> listBuyAudit(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize);

    /**
     * 采购审核通过
     * @param orderId 采购订单ID
     * @param auditor 审核人
     */
    void buyAuditApprove(Long orderId, Employee auditor);

    /**
     * 采购审核不通过
     * @param orderId 采购订单ID
     * @param auditor 审核人
     */
    void buyAuditReject(Long orderId, Employee auditor);

    Integer countTask(OrderQueryModel orderQueryModel);

    List<Order> listTask(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize);

    void assignTask(Long orderId, Employee merchandiser);

    Integer countMyTask(OrderQueryModel orderQueryModel, Employee employee);

    List<Order> listMyTask(OrderQueryModel orderQueryModel, Integer pageNum, Integer pageSize, Employee employee);

    void endTask(Long orderId);

}
