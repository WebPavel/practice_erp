package cn.com.zv2.auth.menu.web;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.menu.entity.Menu;
import cn.com.zv2.auth.menu.entity.MenuQueryModel;
import cn.com.zv2.auth.menu.entity.TreeViewItem;
import cn.com.zv2.auth.menu.service.MenuService;
import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.auth.role.service.RoleService;
import cn.com.zv2.util.base.BaseAction;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuAction extends BaseAction {

    public Long parentId;
    public Long[] roleIds;
    public Menu menu = new Menu();
    public MenuQueryModel menuQueryModel = new MenuQueryModel();
    private MenuService menuService;
    private RoleService roleService;

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public String list() {
        List<Menu> parentList = menuService.listSelectableMenu();
        put("parentList", parentList);
        setTotalRow(menuService.count(menuQueryModel));
        List<Menu> menuList = menuService.list(menuQueryModel, pageNum, pageSize);
        put("menuList", menuList);
        return LIST;
    }

    public String edit() {
        // 加载所有角色
        List<Role> roleList = roleService.list();
        put("roleList", roleList);
        List<Menu> parentList = menuService.listSelectableMenu();
        put("parentList", parentList);
        if (menu.getId() != null) {
            menu = menuService.get(menu.getId());
            if (menu.getParent() != null) {
                parentId = menu.getParent().getId();
                // 回显角色列表
                roleIds = new Long[menu.getRoles().size()];
                int i = 0;
                for (Role role : menu.getRoles()) {
                    roleIds[i++] = role.getId();
                }
            }
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (menu.getId() == null) {
            menuService.save(menu, parentId, roleIds);
        } else {
            menuService.update(menu, parentId, roleIds);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        menuService.delete(menu);
        return REDIRECT_LIST;
    }

    /**
     * 显示菜单
     * @return 返回JSON数组
     */
//    public void showMenu() throws IOException {
//        String root = getRequest().getParameter("root");
//        StringBuilder jsonData = new StringBuilder();
//        jsonData.append("[");
//        if ("source".equals(root)) {
//            // 获取当前用户能操作的所有一级菜单
//            List<Menu> menuList = menuService.listLevel1MenuByEmployee(getSessionEmployee().getId());
//            for (Menu m : menuList) {
//                jsonData.append("{\"text\":\"");
//                jsonData.append(m.getName());
//                jsonData.append("\",\"classes\":\"folder\",\"hasChildren\":true,\"id\":\"");
//                jsonData.append(m.getId());
//                jsonData.append("\"},");
//            }
//        } else {
//            // 获取指定一级菜单的二级菜单项
//            Long parentId = Long.parseLong(root);
//            List<Menu> menuList = menuService.listByEmployeeAndParent(getSessionEmployee().getId(), parentId);
//            for (Menu m : menuList) {
//                jsonData.append("{\"text\":\"");
//                jsonData.append(m.getName());
//                jsonData.append("\",\"classes\":\"file\",\"hasChildren\":false},");
//            }
//        }
//        jsonData.deleteCharAt(jsonData.length() - 1);
//        jsonData.append("]");
//        HttpServletResponse response = getResponse();
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        out.write(jsonData.toString());
//        out.flush();
//    }

    public void showMenu() throws IOException {
        String root = getRequest().getParameter("root");
        List<TreeViewItem> treeViewItems = new ArrayList<>();
        if ("source".equals(root)) {
            // 获取当前用户能操作的所有一级菜单
            List<Menu> menuList = menuService.listLevel1MenuByEmployee(getSessionEmployee().getId());
            for (Menu m : menuList) {
                TreeViewItem item = new TreeViewItem();
                item.setId(m.getId().toString());
                item.setText(m.getName());
                item.setClasses("folder");
                item.setHasChildren(true);
                treeViewItems.add(item);
            }
        } else {
            // 获取指定一级菜单的二级菜单项
            Long parentId = Long.parseLong(root);
            List<Menu> menuList = menuService.listByEmployeeAndParent(getSessionEmployee().getId(), parentId);
            for (Menu m : menuList) {
                TreeViewItem item = new TreeViewItem();
                item.setId(m.getId().toString());
                StringBuilder text = new StringBuilder();
                text.append("<a class='black' target='main' href='");
                text.append(m.getUrl());
                text.append("'>");
                text.append(m.getName());
                text.append("</a>");
                item.setText(text.toString());
                item.setClasses("file");
                item.setHasChildren(false);
                treeViewItems.add(item);
            }
        }
        HttpServletResponse response = getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(treeViewItems));
        out.flush();
    }

    private Employee getSessionEmployee() {
        return (Employee) getSession(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME);
    }

}
