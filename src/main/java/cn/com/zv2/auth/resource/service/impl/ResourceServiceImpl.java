package cn.com.zv2.auth.resource.service.impl;

import cn.com.zv2.auth.resource.dao.ResourceDao;
import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.auth.resource.service.ResourceService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class ResourceServiceImpl implements ResourceService {

    private ResourceDao resourceDao;

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public void save(Resource resource) {
        resourceDao.save(resource);
    }

    @Override
    public void update(Resource resource) {
        resourceDao.update(resource);
    }

    @Override
    public void delete(Resource resource) {
        resourceDao.delete(resource);
    }

    @Override
    public Resource get(Serializable id) {
        return resourceDao.get(id);
    }

    @Override
    public List<Resource> list() {
        return resourceDao.list();
    }

    @Override
    public List<Resource> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return resourceDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return resourceDao.count(baseQueryModel);
    }

    @Override
    public List<Resource> listByEmployee(Long id) {
        return resourceDao.listByEmployeeId(id);
    }
}
