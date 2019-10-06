package cn.com.zv2.auth.resource.web;

import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.auth.resource.entity.ResourceQueryModel;
import cn.com.zv2.auth.resource.service.ResourceService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class ResourceAction extends BaseAction {

    public Resource resource = new Resource();
    public ResourceQueryModel resourceQueryModel = new ResourceQueryModel();
    private ResourceService resourceService;

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public String list() {
        setTotalRow(resourceService.count(resourceQueryModel));
        List<Resource> resourceList = resourceService.list(resourceQueryModel, pageNum, pageSize);
        put("resourceList", resourceList);
        return LIST;
    }

    public String edit() {
        if (resource.getId() != null) {
            resource = resourceService.get(resource.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (resource.getId() == null) {
            resourceService.save(resource);
        } else {
            resourceService.update(resource);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        resourceService.delete(resource);
        return REDIRECT_LIST;
    }

}
