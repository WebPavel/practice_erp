package cn.com.zv2.auth.manager.web;

import cn.com.zv2.auth.manager.entity.Manager;
import cn.com.zv2.auth.manager.entity.ManagerQueryModel;
import cn.com.zv2.auth.manager.service.ManagerService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class ManagerAction extends BaseAction {

    public Manager manager = new Manager();
    public ManagerQueryModel managerQueryModel = new ManagerQueryModel();
    private ManagerService managerService;

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    public String list() {
        setTotalRow(managerService.count(managerQueryModel));
        List<Manager> managerList = managerService.list(managerQueryModel, pageNum, pageSize);
        put("managerList", managerList);
        return LIST;
    }

    public String edit() {
        if (manager.getId() != null) {
            manager = managerService.get(manager.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (manager.getId() == null) {
            managerService.save(manager);
        } else {
            managerService.update(manager);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        managerService.delete(manager);
        return REDIRECT_LIST;
    }

}
