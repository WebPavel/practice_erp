package cn.com.zv2.auth.menu.entity;

import java.util.Set;

/**
 * @author lb
 * @date 2019/10/8 21:50
 */
public class Menu {

    public static final Long MENU_SYSTEM_MENU_ID = 1L;

    private Long id;
    private String name;
    private String url;

    /** 父菜单 */
    private Menu parent;
    private Set<Menu> children;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Set<Menu> getChildren() {
        return children;
    }

    public void setChildren(Set<Menu> children) {
        this.children = children;
    }

}
