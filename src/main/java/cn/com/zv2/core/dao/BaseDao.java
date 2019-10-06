package cn.com.zv2.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author lb
 * @date 2019/9/21 2:00
 */
public interface BaseDao<T> {
    Serializable save(T model);
    void update(T model);
    void delete(T model);
    T get(Serializable id);
    List<T> list(int pageNum, int pageSize);
    List<T> list(T queryModel, int pageNum, int pageSize);
    Long count();
    Long count(T queryModel);
}
