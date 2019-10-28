package cn.com.zv2.util.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author lb
 * @date 2019/10/28 19:10
 */
public class JFreeChartUtils {

    static {
        StandardChartTheme theme = new StandardChartTheme("unicode") {
            @Override
            public void apply(JFreeChart chart) {
                chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
                super.apply(chart);
            }
        };
        theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 18));
        theme.setLargeFont(new Font("宋体", Font.PLAIN, 16));
        theme.setRegularFont(new Font("宋体", Font.PLAIN, 14));
        theme.setSmallFont(new Font("宋体", Font.PLAIN, 12));
        ChartFactory.setChartTheme(theme);
    }

    public static PieDataset createPieDataset(Map<Object, Object> dataMap) {
        DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
        for (Map.Entry entry : dataMap.entrySet()) {
            localDefaultPieDataset.setValue(entry.getKey().toString(), new Double(entry.getValue().toString()));
        }
        return localDefaultPieDataset;
    }

    public static JFreeChart createChart(String pieChartTitle, PieDataset paramPieDataset, String titleToolTip) {
        JFreeChart localJFreeChart = ChartFactory.createPieChart(pieChartTitle, paramPieDataset, true, true, false);
        localJFreeChart.setTitle(new TextTitle(pieChartTitle, new Font("微软雅黑", Font.BOLD, 18)));
        TextTitle localTextTitle = localJFreeChart.getTitle();
        localTextTitle.setToolTipText(titleToolTip);
        PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
        localPiePlot.setLabelFont(new Font("微软雅黑", Font.BOLD, 16));
        localPiePlot.setNoDataMessage("No data available");
        localPiePlot.setCircular(false);
        localPiePlot.setLabelGap(0.02D);
        // 设置图下方的图例说明字体
        localJFreeChart.getLegend().setItemFont(new Font("微软雅黑", Font.BOLD, 14));
        return localJFreeChart;
    }

    public static void writeChartAsImg(String pieChartTitle, Map dataMap, String titleToolTip, OutputStream outputStream) throws IOException {
        PieDataset paramPieDataset = createPieDataset(dataMap);
        JFreeChart jfc = createChart(pieChartTitle, paramPieDataset, titleToolTip);
        BufferedImage bi = jfc.createBufferedImage(480, 360);
        ImageIO.write(bi, "PNG", outputStream);
    }
}
