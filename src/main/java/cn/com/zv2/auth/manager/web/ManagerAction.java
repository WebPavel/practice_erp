package cn.com.zv2.auth.manager.web;

import cn.com.zv2.auth.manager.entity.Manager;
import cn.com.zv2.auth.manager.service.ManagerService;
import cn.com.zv2.util.base.BaseAction;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerAction extends BaseAction {

    private ManagerService managerService;

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    private Map<String, Object> data = new HashMap<>();
    private Map<String, Object> result = new HashMap<>();

    public Map<String, Object> getData() {
        return data;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public String get_users() {
        Manager queryModel = new Manager();
        queryModel.setFirstname(getRequest().getParameter("firstname"));
        queryModel.setLastname(getRequest().getParameter("lastname"));
        queryModel.setPhone(getRequest().getParameter("phone"));
        queryModel.setEmail(getRequest().getParameter("email"));
        long total = managerService.count(queryModel);
        List<Manager> rows = managerService.list(queryModel, Integer.parseInt(getRequest().getParameter("page")), Integer.parseInt(getRequest().getParameter("rows")));
        data.put("total", total);
        data.put("rows", rows);
        return "get_users";
    }

    public String save_user() {
        Manager manager = new Manager();
        manager.setFirstname(getRequest().getParameter("firstname"));
        manager.setLastname(getRequest().getParameter("lastname"));
        manager.setPhone(getRequest().getParameter("phone"));
        manager.setEmail(getRequest().getParameter("email"));
        Serializable id = managerService.save(manager);
        if (id != null) {
            result.put("success", true);
            result.put("errorMsg", "");
        } else {
            result.put("success", false);
            result.put("errorMsg", "save happen error");
        }
        return "save_user";
    }

    public String update_user() {
        Manager manager = new Manager();
        manager.setId(Long.parseLong(getRequest().getParameter("id")));
        manager.setFirstname(getRequest().getParameter("firstname"));
        manager.setLastname(getRequest().getParameter("lastname"));
        manager.setPhone(getRequest().getParameter("phone"));
        manager.setEmail(getRequest().getParameter("email"));
        boolean success = managerService.update(manager);
        if (success) {
            result.put("success", success);
            result.put("errorMsg", "");
        } else {
            result.put("success", false);
            result.put("errorMsg", "update happen error");
        }
        return "update_user";
    }

    public String delete_user() {
        boolean success = managerService.delete(Long.parseLong(getRequest().getParameter("id")));
        if (success) {
            result.put("success", success);
            result.put("errorMsg", "");
        } else {
            result.put("success", false);
            result.put("errorMsg", "delete happen error");
        }
        return "delete_user";
    }

}
