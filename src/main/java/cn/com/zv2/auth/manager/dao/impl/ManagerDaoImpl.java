package cn.com.zv2.auth.manager.dao.impl;

import cn.com.zv2.auth.manager.dao.ManagerDao;
import cn.com.zv2.auth.manager.entity.Manager;
import cn.com.zv2.auth.manager.entity.ManagerQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements ManagerDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        ManagerQueryModel managerQueryModel = (ManagerQueryModel) baseQueryModel;

    }
}
