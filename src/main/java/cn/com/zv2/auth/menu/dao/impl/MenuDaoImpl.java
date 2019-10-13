package cn.com.zv2.auth.menu.dao.impl;

import cn.com.zv2.auth.menu.dao.MenuDao;
import cn.com.zv2.auth.menu.entity.Menu;
import cn.com.zv2.auth.menu.entity.MenuQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        MenuQueryModel menuQueryModel = (MenuQueryModel) baseQueryModel;
        // 过滤系统菜单
        detachedCriteria.add(Restrictions.not(Restrictions.eq("id", Menu.MENU_SYSTEM_MENU_ID)));
        if (menuQueryModel.getName() != null && menuQueryModel.getName().trim().length() > 0) {
            detachedCriteria.add(Restrictions.like("name", "%" + menuQueryModel.getName().trim() + "%"));
        }
        if (menuQueryModel.getParentId() != null && menuQueryModel.getParentId() != -1) {
            detachedCriteria.add(Restrictions.eq("parent.id", menuQueryModel.getParentId()));
        }
    }

    @Override
    public List<Menu> listByParentIdIsOneOrZero() {
        String hql = "from Menu where parent.id = ? or id = ?";
        return this.getHibernateTemplate().find(hql, Menu.MENU_SYSTEM_MENU_ID, Menu.MENU_SYSTEM_MENU_ID);
    }

    @Override
    public List<Menu> listLevel1MenuByEmployeeId(Long employeeId) {
        String hql = "select distinct menu from Menu menu join menu.roles role join role.employees employee where employee.id = ? and menu.parent.id = ? order by menu.id";
        return this.getHibernateTemplate().find(hql, employeeId, Menu.MENU_SYSTEM_MENU_ID);
    }

    @Override
    public List<Menu> listByEmployeeIdAndParentId(Long employeeId, Long parentId) {
        String hql = "select distinct menu from Menu menu join menu.roles role join role.employees employee where employee.id = ? and menu.parent.id = ? order by menu.id";
        return this.getHibernateTemplate().find(hql, employeeId, parentId);
    }
}
