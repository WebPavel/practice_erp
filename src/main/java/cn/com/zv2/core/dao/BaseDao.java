package cn.com.zv2.core.dao;

import cn.com.zv2.core.entity.BaseQueryModel;

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
    List<T> list();
    List<T> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize);
    Integer count(BaseQueryModel baseQueryModel);
}
