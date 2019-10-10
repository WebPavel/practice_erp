package cn.com.zv2.auth.menu.web;

import cn.com.zv2.auth.menu.entity.Menu;
import cn.com.zv2.auth.menu.entity.MenuQueryModel;
import cn.com.zv2.auth.menu.service.MenuService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class MenuAction extends BaseAction {

    public Long parentId;
    public Menu menu = new Menu();
    public MenuQueryModel menuQueryModel = new MenuQueryModel();
    private MenuService menuService;

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public String list() {
        List<Menu> parentList = menuService.listLevel1Menu();
        put("parentList", parentList);
        setTotalRow(menuService.count(menuQueryModel));
        List<Menu> menuList = menuService.list(menuQueryModel, pageNum, pageSize);
        put("menuList", menuList);
        return LIST;
    }

    public String edit() {
        List<Menu> parentList = menuService.listLevel1Menu();
        put("parentList", parentList);
        if (menu.getId() != null) {
            menu = menuService.get(menu.getId());
            if (menu.getParent() != null) {
                parentId = menu.getParent().getId();
            }
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (menu.getId() == null) {
            menuService.save(menu, parentId);
        } else {
            menuService.update(menu, parentId);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        menuService.delete(menu);
        return REDIRECT_LIST;
    }

}
