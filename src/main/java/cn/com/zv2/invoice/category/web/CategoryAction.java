package cn.com.zv2.invoice.category.web;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.invoice.category.entity.CategoryQueryModel;
import cn.com.zv2.invoice.category.service.CategoryService;
import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.invoice.supplier.service.SupplierService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class CategoryAction extends BaseAction {

    public Long supplierId;
    public Category category = new Category();
    public CategoryQueryModel categoryQueryModel = new CategoryQueryModel();
    private CategoryService categoryService;
    private SupplierService supplierService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public String list() {
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        categoryQueryModel.setSupplier(supplier);
        List<Supplier> supplierList = supplierService.list();
        put("supplierList", supplierList);
        setTotalRow(categoryService.count(categoryQueryModel));
        List<Category> categoryList = categoryService.list(categoryQueryModel, pageNum, pageSize);
        put("categoryList", categoryList);
        return LIST;
    }

    public String edit() {
        List<Supplier> supplierList = supplierService.list();
        put("supplierList", supplierList);
        if (category.getId() != null) {
            category = categoryService.get(category.getId());
            if (category.getSupplier() != null) {
                supplierId = category.getSupplier().getId();
            }
        }
        return EDIT;
    }

    public String updateIfPresent() {
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        category.setSupplier(supplier);
        if (category.getId() == null) {
            categoryService.save(category);
        } else {
            categoryService.update(category);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        categoryService.delete(category);
        return REDIRECT_LIST;
    }

}
