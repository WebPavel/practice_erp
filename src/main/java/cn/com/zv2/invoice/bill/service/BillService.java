package cn.com.zv2.invoice.bill.service;

import cn.com.zv2.invoice.bill.entity.BillQueryModel;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import jxl.write.WriteException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface BillService {

    List<Object[]> listBuyBill(BillQueryModel billQueryModel);

    List<OrderDetail> listBuyBillByProduct(BillQueryModel billQueryModel);

    void writeJFreeChart(OutputStream outputStream, BillQueryModel billQueryModel) throws IOException;

    InputStream getWriteExcelStream(BillQueryModel billQueryModel) throws IOException, WriteException;
}
