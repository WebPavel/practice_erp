package cn.com.zv2.auth.menu.entity;

/**
 * @author lb
 * @date 2019/10/12 1:07
 */
public class TreeViewItem {

    private String id;
    private String text;
    private String classes;
    private Boolean hasChildren;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

}
