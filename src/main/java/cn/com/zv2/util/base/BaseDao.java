package cn.com.zv2.util.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author lb
 * @date 2019/9/22 1:17
 */
public interface BaseDao<T> {
    Serializable save(T model);
    void update(T model);
    void delete(T model);
    T get(Serializable id);
    List<T> list();
    List<T> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize);
    Integer count(BaseQueryModel baseQueryModel);
}
