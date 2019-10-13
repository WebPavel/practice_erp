package cn.com.zv2.auth.menu.service;

import cn.com.zv2.auth.menu.entity.Menu;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MenuService extends BaseService<Menu> {

    /**
     * 获取系统菜单和所有一级菜单
     * @return
     */
    List<Menu> listSelectableMenu();

    void save(Menu menu, Long parentId, Long[] roleIds);

    void update(Menu menu, Long parentId, Long[] roleIds);

    List<Menu> listLevel1MenuByEmployee(Long employeeId);

    /**
     * 获取指定用户对应的指定一级菜单可操作的二级菜单
     * @param employeeId 用户ID
     * @param parentId 一级菜单ID
     * @return
     */
    List<Menu> listByEmployeeAndParent(Long employeeId, Long parentId);

}
