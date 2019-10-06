package cn.com.zv2.auth.role.dao.impl;

import cn.com.zv2.auth.role.dao.RoleDao;
import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.auth.role.entity.RoleQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        RoleQueryModel roleQueryModel = (RoleQueryModel) baseQueryModel;
        if (roleQueryModel.getName() != null && roleQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + roleQueryModel.getName().trim() + "%"));
        }
        if (roleQueryModel.getCode() != null && roleQueryModel.getCode().trim().length() > 0) {
            detachedCriteria.add(Restrictions.eq("code", roleQueryModel.getCode()));
        }
    }
}
