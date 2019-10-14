package cn.com.zv2.invoice.category.service.impl;

import cn.com.zv2.invoice.category.dao.CategoryDao;
import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.invoice.category.service.CategoryService;
import cn.com.zv2.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public Category get(Serializable id) {
        return categoryDao.get(id);
    }

    @Override
    public List<Category> list() {
        return categoryDao.list();
    }

    @Override
    public List<Category> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {
        return categoryDao.list(baseQueryModel, pageNum, pageSize);
    }

    @Override
    public Integer count(BaseQueryModel baseQueryModel) {
        return categoryDao.count(baseQueryModel);
    }

}
