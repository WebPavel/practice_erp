package cn.com.zv2.auth.department.dao.impl;

import cn.com.zv2.auth.department.dao.DepartmentDao;
import cn.com.zv2.auth.department.entity.Department;
import cn.com.zv2.auth.department.entity.DepartmentQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * @author lb
 * @date 2019/9/19 2:17
 */
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        DepartmentQueryModel departmentQueryModel = (DepartmentQueryModel) baseQueryModel;
        if (departmentQueryModel.getName() != null && departmentQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + departmentQueryModel.getName().trim() + "%"));
        }
        if (departmentQueryModel.getTelephone() != null && departmentQueryModel.getTelephone().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("telephone", "%" + departmentQueryModel.getTelephone().trim() + "%"));
        }
    }
}
