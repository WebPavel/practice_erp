package cn.com.zv2.auth.employee.dao.impl;

import cn.com.zv2.auth.employee.dao.EmployeeDao;
import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.employee.entity.EmployeeQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        EmployeeQueryModel employeeQueryModel = (EmployeeQueryModel) baseQueryModel;

    }

    @Override
    public Employee getByUsernameAndPassword(String username, String password) {
        String hql = "from Employee where username = ? and password = ?";
        List<Employee> temp = this.getHibernateTemplate().find(hql, username, password);
        return temp.size() > 0 ? temp.get(0) : null;
    }

}
