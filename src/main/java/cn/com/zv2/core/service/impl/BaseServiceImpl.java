package cn.com.zv2.core.service.impl;

import cn.com.zv2.core.dao.BaseDao;

import java.io.Serializable;
import java.util.List;

/**
 * @author lb
 * @date 2019/11/1 21:17
 */
public class BaseServiceImpl<T> {
    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public Serializable save(T model) {
        return baseDao.save(model);
    }

    public boolean update(T model) {
        return baseDao.update(model);
    }

    public boolean delete(Serializable id) {
        return baseDao.delete(id);
    }

    public boolean delete(T model) {
        return baseDao.delete(model);
    }

    public T get(Serializable id) {
        return baseDao.get(id);
    }

    public List<T> list() {
        return baseDao.list();
    }

    public List<T> list(int pageNum, int pageSize) {
        return baseDao.list(pageNum, pageSize);
    }

    public List<T> list(T queryModel, int pageNum, int pageSize) {
        return baseDao.list(queryModel, pageNum, pageSize);
    }

    public long count() {
        return baseDao.count();
    }

    public long count(T queryModel) {
        return baseDao.count(queryModel);
    }

    public List<Object[]> executeSql(String sql, Object[] params) {
        return baseDao.executeSql(sql, params);
    }

    public boolean batchUpdate(String sqlTemplate, List<Object[]> list) {
        return baseDao.batchUpdate(sqlTemplate, list);
    }

    public boolean batchUpdate(String sqlTemplate, Object fieldValue, Serializable[] ids) {
        return baseDao.batchUpdate(sqlTemplate, fieldValue, ids);
    }

}
