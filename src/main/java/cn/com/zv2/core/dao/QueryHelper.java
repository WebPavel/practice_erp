package cn.com.zv2.core.dao;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lb
 * @date 2019/11/2 5:09
 */
public class QueryHelper {

    public static final Integer ORDER_BY_ASC = 1;
    public static final Integer ORDER_BY_DESC = 2;

    private static final String ORDER_BY_ASC_VALUE = "asc";
    private static final String ORDER_BY_DESC_VALUE = "desc";

    private static final Map<Integer, String> orderByMap = new HashMap<>();

    static {
        orderByMap.put(ORDER_BY_ASC, ORDER_BY_ASC_VALUE);
        orderByMap.put(ORDER_BY_DESC, ORDER_BY_DESC_VALUE);
    }

    private String fromClause = "";
    private String whereClause = "";
    private String orderByClause = "";
    private List<Object> objectList;

    /**
     * 指定查询哪张表
     * @param clazz 实体类
     * @param alias  别名
     */
    public QueryHelper(Class clazz, String alias) {
        fromClause = " FROM " + clazz.getSimpleName() + " " + alias;
    }

    public QueryHelper addCondition(String condition, Object... objects) {
        if (whereClause.length() > 0) {
            whereClause += " AND " + condition;
        } else {
            whereClause += " WHERE " + condition;
        }
        if (objects == null) {
            objectList = new ArrayList<>();
        }
        for (Object object : objects) {
            objectList.add(object);
        }
        return this;
    }

    public QueryHelper orderBy(String property, int orderBy) {
        Assert.isTrue(orderByMap.containsKey(orderBy), "order by error");
        if (orderByClause.length() > 0) {
            orderByClause += "," + property + " " + orderByMap.get(orderBy);
        } else {
            orderByClause += " ORDER BY " + property + " " + orderByMap.get(orderBy);
        }
        return this;
    }

    public String buildHQL() {
        return fromClause + whereClause + orderByClause;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

}
