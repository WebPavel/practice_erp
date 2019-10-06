package cn.com.zv2.util.base;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author lb
 * @date 2019/9/22 1:25
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport {

    private Class<T> entityClass;

    /**
     * 初始化entityClass
     */
    public BaseDaoImpl() {
        Type genericType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genericType).getActualTypeArguments();
        entityClass = (Class<T>) params[0];
    }

    public Serializable save(T model) {
        return this.getHibernateTemplate().save(model);
    }

    public void update(T model) {
        this.getHibernateTemplate().update(model);
    }

    public void delete(T model) {
        this.getHibernateTemplate().delete(model);
    }

    public T get(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    public List<T> list() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        return this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    public List<T> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        doQbc(detachedCriteria, baseQueryModel);
        return this.getHibernateTemplate().findByCriteria(detachedCriteria, (pageNum - 1) * pageSize, pageSize);
    }

    public Integer count(BaseQueryModel baseQueryModel) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        detachedCriteria.setProjection(Projections.rowCount());
        doQbc(detachedCriteria, baseQueryModel);
        List<Long> count = this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return count.get(0).intValue();
    }

    public abstract void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel);
}
