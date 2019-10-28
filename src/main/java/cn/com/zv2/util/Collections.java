package cn.com.zv2.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lb
 * @date 2019/10/28 19:40
 */
public class Collections {

    public static Map convertList(List<Object[]> list) {
        Map map = new LinkedHashMap<>();
        for (Object[] objArr : list) {
            map.put(objArr[0], objArr[1]);
        }
        return map;
    }
}
