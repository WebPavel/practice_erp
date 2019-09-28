package cn.com.zv2.auth.department.service.impl;

import cn.com.zv2.auth.department.dao.DepartmentDao;
import cn.com.zv2.auth.department.entity.Department;
import cn.com.zv2.auth.department.service.DepartmentService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author lb
 * @date 2019/9/19 2:18
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public void delete(Department department) {
        departmentDao.delete(department);
    }

    @Override
    public Department get(Serializable id) {
        return departmentDao.get(id);
    }

    @Override
    public List<Department> list() {
        return departmentDao.list();
    }

    @Override
    public List<Department> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return departmentDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return departmentDao.count(baseQueryModel);
    }
}
