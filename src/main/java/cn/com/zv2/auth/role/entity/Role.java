package cn.com.zv2.auth.role.entity;

import cn.com.zv2.auth.menu.entity.Menu;
import cn.com.zv2.auth.resource.entity.Resource;

import java.util.Set;

/**
 * @author lb
 * @date 2019/10/4 1:33
 */
public class Role {

    private Long id;
    private String name;
    private String code;
    private Set<Resource> resources;
    private Set<Menu> menus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

}
