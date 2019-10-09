package cn.com.zv2.auth.role.web;

import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.auth.resource.service.ResourceService;
import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.auth.role.entity.RoleQueryModel;
import cn.com.zv2.auth.role.service.RoleService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class RoleAction extends BaseAction {

    public Long[] resourceIds;
    public Role role = new Role();
    public RoleQueryModel roleQueryModel = new RoleQueryModel();
    private RoleService roleService;
    private ResourceService resourceService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public String list() {
        setTotalRow(roleService.count(roleQueryModel));
        List<Role> roleList = roleService.list(roleQueryModel, pageNum, pageSize);
        put("roleList", roleList);
        return LIST;
    }

    public String edit() {
        List<Resource> resourceList = resourceService.list();
        put("resourceList", resourceList);
        if (role.getId() != null) {
            role = roleService.get(role.getId());
            resourceIds = new Long[role.getResources().size()];
            int i = 0;
            for (Resource resource : role.getResources()) {
                resourceIds[i++] = resource.getId();
            }
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (role.getId() == null) {
            roleService.save(role, resourceIds);
        } else {
            roleService.update(role, resourceIds);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        roleService.delete(role);
        return REDIRECT_LIST;
    }

}
