package cn.com.zv2.auth.role.service.impl;

import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.auth.role.dao.RoleDao;
import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.auth.role.service.RoleService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void save(Role role) {
    }

    @Override
    public void update(Role role) {
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Override
    public Role get(Serializable id) {
        return roleDao.get(id);
    }

    @Override
    public List<Role> list() {
        return roleDao.list();
    }

    @Override
    public List<Role> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return roleDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return roleDao.count(baseQueryModel);
    }

    @Override
    public void save(Role role, Long[] resourceIds) {
        Set<Resource> resources = new HashSet<>();
        for (Long resourceId : resourceIds) {
            Resource resource = new Resource();
            resource.setId(resourceId);
            resources.add(resource);
        }
        role.setResources(resources);
        roleDao.save(role);
    }

    @Override
    public void update(Role role, Long[] resourceIds) {
        Set<Resource> resources = new HashSet<>();
        for (Long resourceId : resourceIds) {
            Resource resource = new Resource();
            resource.setId(resourceId);
            resources.add(resource);
        }
        role.setResources(resources);
        roleDao.update(role);
    }
}
