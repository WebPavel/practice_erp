package cn.com.zv2.auth.manager.service.impl;

import cn.com.zv2.auth.manager.dao.ManagerDao;
import cn.com.zv2.auth.manager.entity.Manager;
import cn.com.zv2.auth.manager.service.ManagerService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao;

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public void save(Manager manager) {
        managerDao.save(manager);
    }

    @Override
    public void update(Manager manager) {
        managerDao.update(manager);
    }

    @Override
    public void delete(Manager manager) {
        managerDao.delete(manager);
    }

    @Override
    public Manager get(Serializable id) {
        return managerDao.get(id);
    }

    @Override
    public List<Manager> list() {
        return managerDao.list();
    }

    @Override
    public List<Manager> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return managerDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return managerDao.count(baseQueryModel);
    }

}
