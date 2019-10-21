package cn.com.zv2.util.number;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 流水号生成器
 * @author lb
 * @date 2019/10/21 1:09
 */
public class SerialNumberUtils {

    public static AtomicInteger serialNumber = new AtomicInteger(1);
    /** 默认数量级(百万) */
    public static int defaultNumberScale = 7;

    private static final byte[] zeros = {48, 48, 48, 48, 48, 48, 48};

    public static final String generateSN() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyMMdd");
        String part1 = df.format(date);
        int num = serialNumber.getAndIncrement();
        int numLength = String.valueOf(num).length();
        String part2 = new String(zeros, 0, defaultNumberScale - numLength);
        return Long.toHexString(Long.parseLong(part1 + part2 + num)).toUpperCase();
    }

}
