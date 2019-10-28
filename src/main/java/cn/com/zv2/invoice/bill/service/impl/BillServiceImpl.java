package cn.com.zv2.invoice.bill.service.impl;

import cn.com.zv2.invoice.bill.dao.BillDao;
import cn.com.zv2.invoice.bill.entity.BillQueryModel;
import cn.com.zv2.invoice.bill.service.BillService;
import cn.com.zv2.invoice.orderdetail.entity.OrderDetail;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.util.jfreechart.JFreeChartUtils;
import cn.com.zv2.util.office.ExcelUtils;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BillServiceImpl implements BillService {

    private BillDao billDao;

    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    public List<Object[]> listBuyBill(BillQueryModel billQueryModel) {
        return billDao.listBuyBill(billQueryModel);
    }

    @Override
    public List<OrderDetail> listBuyBillByProduct(BillQueryModel billQueryModel) {
        return billDao.listOrderDetailByProduct(billQueryModel);
    }

    @Override
    public void writeJFreeChart(OutputStream outputStream, BillQueryModel billQueryModel) throws IOException {
        List<Object[]> buyBillList = billDao.listBuyBill(billQueryModel);
        Map map = new LinkedHashMap<>();
        for (Object[] objArr : buyBillList) {
            Product p = (Product) objArr[0];
            map.put(p.getName(), objArr[1]);
        }
        JFreeChartUtils.writeChartAsImg("采购报表", map, "A title tooltip!", outputStream);
    }

    @Override
    public InputStream getWriteExcelStream(BillQueryModel billQueryModel) throws IOException, WriteException {
        List<Object[]> buyBillList = billDao.listBuyBill(billQueryModel);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableWorkbook workbook = ExcelUtils.createExcel(baos);
        WritableSheet sheet = ExcelUtils.createSheet(workbook, 1, "总括");

        // 设置单元格宽度
        ExcelUtils.setColumnSize(sheet, 1, 8);
        ExcelUtils.setColumnSize(sheet, 2, 8);
        ExcelUtils.setColumnSize(sheet, 3, 25);
        ExcelUtils.setColumnSize(sheet, 4, 25);
        ExcelUtils.setColumnSize(sheet, 5, 25);
        // 设置单元格高度
        ExcelUtils.setRowSize(sheet, 1, 15);
        ExcelUtils.setRowSize(sheet, 2, 37);
        ExcelUtils.setRowSize(sheet, 3, 6);
        ExcelUtils.setRowSize(sheet, 4, 23);
        // 合并单元格
        ExcelUtils.mergeCells(sheet, 2, 2, 2, 4);
        ExcelUtils.mergeCells(sheet, 3, 2, 3, 5);
        Label label22 = ExcelUtils.newCell(2, 2, "采购统计报表");
        ExcelUtils.setLabelStyle(label22, "黑体", 24, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2020");
        ExcelUtils.appendCell(sheet, label22);
        Label label25 = ExcelUtils.newCell(2, 5, "不限");
        ExcelUtils.setLabelStyle(label25, "黑体", 12, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2002");
        ExcelUtils.appendCell(sheet, label25);
        Label label32 = ExcelUtils.newCell(3, 2, "");
        ExcelUtils.setLabelStyle(label32, "黑体", 1, Colour.BLACK, Colour.GRAY_25, 1, "2022");
        ExcelUtils.appendCell(sheet, label32);
        Label label42 = ExcelUtils.newCell(4, 2, "编号");
        ExcelUtils.setLabelStyle(label42, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
        ExcelUtils.appendCell(sheet, label42);
        Label label43 = ExcelUtils.newCell(4, 3, "供应商");
        ExcelUtils.setLabelStyle(label43, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
        ExcelUtils.appendCell(sheet, label43);
        Label label44 = ExcelUtils.newCell(4, 4, "商品名称");
        ExcelUtils.setLabelStyle(label44, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
        ExcelUtils.appendCell(sheet, label44);
        Label label45 = ExcelUtils.newCell(4, 5, "数量");
        ExcelUtils.setLabelStyle(label45, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2222");
        ExcelUtils.appendCell(sheet, label45);
        int rowStartIdx = 5;
        int i = 0;
        long amount = 0;
        for (Object[] objArr : buyBillList) {
            Product p = (Product) objArr[0];
            Long quantitySum = (Long) objArr[1];
            ExcelUtils.setRowSize(sheet, rowStartIdx+i, 19);

            Label labelI2 = ExcelUtils.newCell(rowStartIdx+i, 2, String.valueOf(i+1));
            ExcelUtils.setLabelStyle(labelI2, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0120");
            ExcelUtils.appendCell(sheet, labelI2);
            Label labelI3 = ExcelUtils.newCell(rowStartIdx+i, 3, p.getCategory().getSupplier().getName());
            ExcelUtils.setLabelStyle(labelI3, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0110");
            ExcelUtils.appendCell(sheet, labelI3);
            Label labelI4 = ExcelUtils.newCell(rowStartIdx+i, 4, p.getName());
            ExcelUtils.setLabelStyle(labelI4, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0110");
            ExcelUtils.appendCell(sheet, labelI4);
            Label labelI5 = ExcelUtils.newCell(rowStartIdx+i, 5, quantitySum.toString());
            ExcelUtils.setLabelStyle(labelI5, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0112");
            ExcelUtils.appendCell(sheet, labelI5);

            i++;
            amount += quantitySum;
        }
        // 设置最后一行高度
        ExcelUtils.setRowSize(sheet, rowStartIdx + i, 25);
        // 合并最后一行单元格
        ExcelUtils.mergeCells(sheet, rowStartIdx + i, 2, rowStartIdx + i, 4);
        Label tailLabelI2 = ExcelUtils.newCell(rowStartIdx+i, 2, "总计：");
        ExcelUtils.setLabelStyle(tailLabelI2, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
        ExcelUtils.appendCell(sheet, tailLabelI2);
        Label tailLabelI5 = ExcelUtils.newCell(rowStartIdx+i, 5, String.valueOf(amount));
        ExcelUtils.setLabelStyle(tailLabelI5, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2222");
        ExcelUtils.appendCell(sheet, tailLabelI5);

        workbook.write();
        workbook.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        return bais;
    }
}
