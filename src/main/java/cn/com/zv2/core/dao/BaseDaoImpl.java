package cn.com.zv2.core.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author lb
 * @date 2019/9/21 2:17
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport {
    private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

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

    public List<T> list(int pageNum, int pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        return this.getHibernateTemplate().findByCriteria(detachedCriteria, (pageNum - 1) * pageSize, pageSize);
    }

    public List<T> list(T queryModel, int pageNum, int pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        doQbc(detachedCriteria, queryModel);
        return this.getHibernateTemplate().findByCriteria(detachedCriteria, (pageNum - 1) * pageSize, pageSize);
    }

    public Long count() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> count = this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return count.get(0);
    }

    public Long count(T queryModel) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        detachedCriteria.setProjection(Projections.rowCount());
        doQbc(detachedCriteria, queryModel);
        List<Long> count = this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return count.get(0);
    }

    private void doQbc(DetachedCriteria detachedCriteria, T queryModel) {
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(queryModel);
                if (value != null) {
                    if (field.getModifiers() == Modifier.PRIVATE) {
                        if (field.isAnnotationPresent(Eq.class)) {
                            detachedCriteria.add(Restrictions.eq(field.getName(), value));
                            continue;
                        }
                        if (field.isAnnotationPresent(Like.class)) {
                            StringBuilder condition = new StringBuilder();
                            condition.append("%");
                            condition.append(value);
                            condition.append("%");
                            detachedCriteria.add(Restrictions.like(field.getName(), condition.toString()));
                            continue;
                        }
                        if (field.isAnnotationPresent(Between.class)) {
                            StringBuilder otherFieldName = new StringBuilder();
                            otherFieldName.append(field.getName());
                            otherFieldName.append("2");
                            Field otherField = entityClass.getDeclaredField(otherFieldName.toString());
                            otherField.setAccessible(true);
                            Object between2 = otherField.get(queryModel);
                            if (value != null && between2 != null) {
                                detachedCriteria.add(Restrictions.between(field.getName(), value, between2));
                                continue;
                            } else if (value != null) {
                                detachedCriteria.add(Restrictions.ge(field.getName(), value));
                                continue;
                            } else {
                                detachedCriteria.add(Restrictions.le(field.getName(), between2));
                                continue;
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
