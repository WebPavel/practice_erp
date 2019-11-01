package cn.com.zv2.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author lb
 * @date 2019/9/21 2:00
 */
public interface BaseDao<T> {

    Serializable save(T model);

    boolean update(T model);

    boolean delete(Serializable id);

    boolean delete(T model);

    T get(Serializable id);

    List<T> list();

    List<T> list(int pageNum, int pageSize);

    List<T> list(T queryModel, int pageNum, int pageSize);

    long count();

    long count(T queryModel);

    List<Object[]> executeSql(String sql, Object[] params);

    boolean batchUpdate(String sqlTemplate, List<Object[]> list);

    boolean batchUpdate(String sqlTemplate, Object fieldValue, Serializable[] ids);

}
