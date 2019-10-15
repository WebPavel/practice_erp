package cn.com.zv2.util.format;

import java.text.DecimalFormat;

/**
 * @author lb
 * @date 2019/10/16 1:41
 */
public class FormatUtils {

    public static String formatCurrency(double currency) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(currency);
    }

}
