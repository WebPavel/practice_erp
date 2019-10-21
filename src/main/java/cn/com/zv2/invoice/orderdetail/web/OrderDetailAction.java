package cn.com.zv2.invoice.orderdetail.web;

import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetailQueryModel;
import cn.com.zv2.invoice.orderdetail.service.OrderDetailService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class OrderDetailAction extends BaseAction {

    public OrderDetail orderDetail = new OrderDetail();
    public OrderDetailQueryModel orderDetailQueryModel = new OrderDetailQueryModel();
    private OrderDetailService orderDetailService;

    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    public String list() {
        setTotalRow(orderDetailService.count(orderDetailQueryModel));
        List<OrderDetail> orderDetailList = orderDetailService.list(orderDetailQueryModel, pageNum, pageSize);
        put("orderDetailList", orderDetailList);
        return LIST;
    }

    public String edit() {
        if (orderDetail.getId() != null) {
            orderDetail = orderDetailService.get(orderDetail.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (orderDetail.getId() == null) {
            orderDetailService.save(orderDetail);
        } else {
            orderDetailService.update(orderDetail);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        orderDetailService.delete(orderDetail);
        return REDIRECT_LIST;
    }

}
