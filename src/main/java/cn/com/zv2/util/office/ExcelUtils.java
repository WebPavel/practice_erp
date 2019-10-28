package cn.com.zv2.util.office;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author lb
 * @date 2019/10/28 21:16
 */
public class ExcelUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);

    public static WritableWorkbook createExcel(OutputStream outputStream) {
        try {
            return Workbook.createWorkbook(outputStream);
        } catch (IOException e) {
            LOG.error("createExcel error: " + e.getMessage(), e);
            return null;
        }
    }

    public static WritableSheet createSheet(WritableWorkbook workbook, int index, String name) {
        return workbook.createSheet(name, index-1);
    }

    public static Label newCell(int row, int column, String text) {
        return new Label(column-1, row-1, text);
    }

    public static void appendCell(WritableSheet sheet, Label label) {
        try {
            sheet.addCell(label);
        } catch (WriteException e) {
            LOG.error("Sheet addCell error: " + e.getMessage(), e);
        }
    }

    public static void setColumnSize(WritableSheet sheet, int index, int width) {
        sheet.setColumnView(index-1, width);
    }

    public static void setRowSize(WritableSheet sheet, int index, int height) {
        try {
            sheet.setRowView(index-1, height*20);
        } catch (RowsExceededException e) {
            LOG.error("setRowSize error: " + e.getMessage(), e);
        }
    }

    /**
     * 单元格合并
     * @param sheet
     * @param row1 起始单元格行
     * @param col1 起始单元格列
     * @param row2 终止单元格行
     * @param col2 终止单元格列
     */
    public static void mergeCells(WritableSheet sheet, int row1, int col1, int row2, int col2) {
        try {
            sheet.mergeCells(col1-1, row1-1, col2-1, row2-1);
        } catch (WriteException e) {
            LOG.error("mergeCells error: " + e.getMessage(), e);
        }
    }

    /**
     * 设置单元格样式
     * @param label
     * @param fontName [String] 字体名称
     * @param fontSize [int] 字体size
     * @param colour [jxl.format.Colour] 字体颜色,Colour常量
     * @param bgColour [jxl.format.Colour] 单元格背景色,Colour常量
     * @param align [int] 对齐方式{0:左,1:中,2:右}
     * @param borderStyle [String] 边框线样式,{0000上下左右都不要边框,1100上下要边框,0011左右要边框,0220下边左边要粗边框}
     */
    public static void setLabelStyle(Label label, String fontName, int fontSize, Colour colour, Colour bgColour, int align, String borderStyle) {
        try {
            if (colour == null) {
                colour = Colour.BLACK;
            }
            if (bgColour == null) {
                bgColour = Colour.WHITE;
            }
            WritableFont wf = new WritableFont(WritableFont.createFont(fontName), fontSize, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, colour);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setBackground(bgColour);
            wcf.setAlignment(Alignment.CENTRE);
            // 设置边框
            if (borderStyle != null && borderStyle.length() == 4) {
                char[] bs = borderStyle.toCharArray();
                if (bs[0] == '1') {
                    wcf.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
                } else if (bs[0] == '2') {
                    wcf.setBorder(Border.TOP, BorderLineStyle.MEDIUM, Colour.BLACK);
                }
                if (bs[1] == '1') {
                    wcf.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
                } else if (bs[1] == '2') {
                    wcf.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM, Colour.BLACK);
                }
                if (bs[2] == '1') {
                    wcf.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
                } else if (bs[2] == '2') {
                    wcf.setBorder(Border.LEFT, BorderLineStyle.MEDIUM, Colour.BLACK);
                }
                if (bs[3] == '1') {
                    wcf.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
                } else if (bs[3] == '2') {
                    wcf.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM, Colour.BLACK);
                }
            }
            label.setCellFormat(wcf);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
