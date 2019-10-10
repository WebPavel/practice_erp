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
    List<Menu> listLevel1Menu();

    void save(Menu menu, Long parentId);

    void update(Menu menu, Long parentId);
}
