package cn.com.zv2.invoice.bill.dao;

import cn.com.zv2.invoice.bill.entity.BillQueryModel;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;

import java.util.List;

public interface BillDao {

    List<Object[]> listBuyBill(BillQueryModel billQueryModel);

    List<OrderDetail> listOrderDetailByProduct(BillQueryModel billQueryModel);
}
