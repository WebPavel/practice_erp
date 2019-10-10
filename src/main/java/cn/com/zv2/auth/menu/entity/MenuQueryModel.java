package cn.com.zv2.auth.menu.entity;

import cn.com.zv2.util.base.BaseQueryModel;

public class MenuQueryModel extends Menu implements BaseQueryModel {

    private Long parentId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
