package cn.com.zv2.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lb
 * @date 2019/9/21 2:17
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDaoImpl.class);

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

    public boolean update(T model) {
        try {
            this.getHibernateTemplate().update(model);
            return true;
        } catch (DataAccessException e) {
            LOG.error("BaseDaoImpl#update error: {}", e);
            return false;
        }
    }

    public boolean delete(T model) {
        try {
            this.getHibernateTemplate().delete(model);
            return true;
        } catch (DataAccessException e) {
            LOG.error("BaseDaoImpl#delete error: {}", e);
            return false;
        }
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

    public List<Object[]> executeSql(String sql, Object[] params) {
        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        for (int i = 0, len = params.length; i < len; i++) {
            sqlQuery.setParameter(i, params[i]);
        }
        return sqlQuery.list();
    }

    public boolean batchUpdate(String sqlTemplate, List<Object[]> list) {
        try {
            SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    PreparedStatement pstatement = null;
                    try {
                        pstatement = connection.prepareStatement(sqlTemplate);
                        connection.setAutoCommit(false);
                        Object[] objects = null;
                        for (int i = 0, size = list.size(); i < size; i++) {
                            objects = list.get(i);
                            for (int j = 0, length = objects.length; j < length; j++) {
                                pstatement.setObject(j + 1, objects[j]);
                            }
                            pstatement.addBatch();
                        }
                        pstatement.executeBatch();
                        connection.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        try {
                            connection.rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    } finally {
                        try {
                            pstatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            return true;
        } catch (HibernateException e) {
            LOG.error("BaseDaoImpl#batchUpdate error: {}", e);
            return false;
        }
    }

    public boolean batchUpdate(String sqlTemplate, Object fieldValue, Serializable[] ids) {
        try {
            SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    PreparedStatement pstatement = null;
                    try {
                        pstatement = connection.prepareStatement(sqlTemplate);
                        connection.setAutoCommit(false);
                        for (int i = 0, length = ids.length; i < length; i++) {
                            pstatement.setObject(1, fieldValue);
                            pstatement.setObject(2, ids[i]);
                            pstatement.addBatch();
                        }
                        pstatement.executeBatch();
                        connection.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        try {
                            connection.rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    } finally {
                        try {
                            pstatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            return true;
        } catch (HibernateException e) {
            LOG.error("BaseDaoImpl#batchUpdate error: {}", e);
            return false;
        }
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
                            otherFieldName.append("to");
                            otherFieldName.append(field.getName());
                            Field otherField = entityClass.getDeclaredField(otherFieldName.toString());
                            otherField.setAccessible(true);
                            Object toValue = otherField.get(queryModel);
                            if (value != null && toValue != null) {
                                detachedCriteria.add(Restrictions.between(field.getName(), value, toValue));
                                continue;
                            } else if (value != null) {
                                detachedCriteria.add(Restrictions.ge(field.getName(), value));
                                continue;
                            } else {
                                detachedCriteria.add(Restrictions.le(field.getName(), toValue));
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
