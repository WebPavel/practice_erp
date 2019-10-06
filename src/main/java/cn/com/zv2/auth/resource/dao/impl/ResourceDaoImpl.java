package cn.com.zv2.auth.resource.dao.impl;

import cn.com.zv2.auth.resource.dao.ResourceDao;
import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.auth.resource.entity.ResourceQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        ResourceQueryModel resourceQueryModel = (ResourceQueryModel) baseQueryModel;
        if (resourceQueryModel.getName() != null && resourceQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + resourceQueryModel.getName() + "%"));
        }
        if (resourceQueryModel.getUrl() != null && resourceQueryModel.getUrl().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("url", "%" + resourceQueryModel.getUrl() + "%"));
        }
    }

    @Override
    public List<Resource> listByEmployeeId(Long id) {
        String hql = "select resource from Employee employee join employee.roleSet role join role.resourceSet resource where employee.id = ?";
        return this.getHibernateTemplate().find(hql, id);
    }
}
