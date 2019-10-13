package cn.com.zv2.auth.menu.service.impl;

import cn.com.zv2.auth.menu.dao.MenuDao;
import cn.com.zv2.auth.menu.entity.Menu;
import cn.com.zv2.auth.menu.service.MenuService;
import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao;

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public void save(Menu menu) {
    }

    @Override
    public void update(Menu menu) {
    }

    @Override
    public void delete(Menu menu) {
        Menu currMenu = menuDao.get(menu.getId());
        menuDao.delete(currMenu);
    }

    @Override
    public Menu get(Serializable id) {
        return menuDao.get(id);
    }

    @Override
    public List<Menu> list() {
        return menuDao.list();
    }

    @Override
    public List<Menu> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return menuDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return menuDao.count(baseQueryModel);
    }

    @Override
    public List<Menu> listSelectableMenu() {
        return menuDao.listByParentIdIsOneOrZero();
    }

    @Override
    public void save(Menu menu, Long parentId, Long[] roleIds) {
        Menu parent = new Menu();
        parent.setId(parentId);
        menu.setParent(parent);

        Set<Role> roles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);
            roles.add(role);
        }
        menu.setRoles(roles);
        menuDao.save(menu);
    }

    @Override
    public void update(Menu menu, Long parentId, Long[] roleIds) {
        // 快照更新
        Menu menuSnapshot = menuDao.get(menu.getId());
        menuSnapshot.setName(menu.getName());
        menuSnapshot.setUrl(menu.getUrl());

        Menu parent = new Menu();
        parent.setId(parentId);
        menuSnapshot.setParent(parent);

        Set<Role> roles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);
            roles.add(role);
        }
        menuSnapshot.setRoles(roles);
    }

    @Override
    public List<Menu> listLevel1MenuByEmployee(Long employeeId) {
        return menuDao.listLevel1MenuByEmployeeId(employeeId);
    }

    @Override
    public List<Menu> listByEmployeeAndParent(Long employeeId, Long parentId) {
        return menuDao.listByEmployeeIdAndParentId(employeeId, parentId);
    }

}
