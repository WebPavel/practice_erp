package cn.com.zv2.auth.manager.service.impl;

import cn.com.zv2.auth.manager.dao.ManagerDao;
import cn.com.zv2.auth.manager.entity.Manager;
import cn.com.zv2.auth.manager.service.ManagerService;
import cn.com.zv2.core.service.impl.BaseServiceImpl;

public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {

    private ManagerDao managerDao;

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
        super.setBaseDao(managerDao);
    }
}
