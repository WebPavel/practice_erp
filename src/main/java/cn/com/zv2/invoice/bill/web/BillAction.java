package cn.com.zv2.invoice.bill.web;

import cn.com.zv2.invoice.bill.entity.BillQueryModel;
import cn.com.zv2.invoice.bill.service.BillService;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.invoice.supplier.service.SupplierService;
import cn.com.zv2.util.base.BaseAction;
import jxl.write.WriteException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class BillAction extends BaseAction {

    private String xlsName;
    private InputStream downloadExcelStream;
    private List<OrderDetail> orderDetailList;
    public BillQueryModel billQueryModel = new BillQueryModel();
    private BillService billService;
    private SupplierService supplierService;

    public String getXlsName() throws UnsupportedEncodingException {
        return new String(xlsName.getBytes("utf-8"), "iso8859-1");
    }

    public InputStream getDownloadExcelStream() {
        return downloadExcelStream;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public String buyBillList() {
        List<Supplier> supplierList = supplierService.list();
        put("supplierList", supplierList);
        List<Object[]> buyBillList = billService.listBuyBill(billQueryModel);
        put("buyBillList", buyBillList);
        for (Object[] objects : buyBillList) {
            for (Object object : objects) {
                System.out.println(object);
            }
            System.out.println("====================================");
        }
        return "buyBillList";
    }

    public String jfreechartPie() throws IOException {
        HttpServletResponse response = getResponse();
        OutputStream os = response.getOutputStream();
        billService.writeJFreeChart(os, billQueryModel);
        os.flush();
        return null;
    }

    public String downloadBuyBill() throws IOException, WriteException {
        xlsName = "采购报表.xls";
        downloadExcelStream = billService.getWriteExcelStream(billQueryModel);
        return "downloadBuyBill";
    }



    // ====================================AJAX====================================
    public String ajaxListBuyBillByProduct() {
        orderDetailList = billService.listBuyBillByProduct(billQueryModel);
        return "ajaxListOrderDetailByProduct";
    }
}
