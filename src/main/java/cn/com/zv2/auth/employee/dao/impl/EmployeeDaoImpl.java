package cn.com.zv2.auth.employee.dao.impl;

import cn.com.zv2.auth.employee.dao.EmployeeDao;
import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.employee.entity.EmployeeQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

    public static final long lastMillisOfDay = 24*60*60*1000 - 1;

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        EmployeeQueryModel employeeQueryModel = (EmployeeQueryModel) baseQueryModel;
        if (employeeQueryModel.getUsername() != null && employeeQueryModel.getUsername().trim().length() > 0) {
            detachedCriteria.add(Restrictions.eq("username", employeeQueryModel.getUsername().trim()));
        }
        if (employeeQueryModel.getName() != null && employeeQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + employeeQueryModel.getName().trim() + "%"));
        }
        if (employeeQueryModel.getTelephone() != null && employeeQueryModel.getTelephone().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("telephone", "%" + employeeQueryModel.getTelephone().trim() + "%"));
        }
        if (employeeQueryModel.getGender() != null && employeeQueryModel.getGender() != -1) {
            detachedCriteria.add(Restrictions.eq("gender", employeeQueryModel.getGender()));
        }
        if (employeeQueryModel.getEmail() != null && employeeQueryModel.getEmail().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("email", "%" + employeeQueryModel.getEmail().trim() + "%"));
        }
        if (employeeQueryModel.getDepartmentId() != null && employeeQueryModel.getDepartmentId() != -1) {
            detachedCriteria.add(Restrictions.eq("department.id", employeeQueryModel.getDepartmentId()));
        }
        if (employeeQueryModel.getBirthday() != null) {
            System.out.println(new Date(employeeQueryModel.getBirthday()));
            detachedCriteria.add(Restrictions.ge("birthday", employeeQueryModel.getBirthday()));
        }
        if (employeeQueryModel.getToBirthday() != null) {
            System.out.println(new Date(employeeQueryModel.getToBirthday()));
            System.out.println(new Date(employeeQueryModel.getToBirthday() + lastMillisOfDay));
            detachedCriteria.add(Restrictions.le("birthday", employeeQueryModel.getToBirthday() + lastMillisOfDay));
        }
    }

    @Override
    public Employee getByUsernameAndPassword(String username, String password) {
        String hql = "from Employee where username = ? and password = ?";
        List<Employee> temp = this.getHibernateTemplate().find(hql, username, password);
        return temp.size() > 0 ? temp.get(0) : null;
    }

    @Override
    public boolean updatePwdByUsernameAndPwd(String username, String password, String newPassword) {
        String hql = "update Employee set password = ? where username = ? and password = ?";
        int updateRow = this.getHibernateTemplate().bulkUpdate(hql, newPassword, username, password);
        return updateRow > 0;
    }

}
