package cn.com.zv2.invoice.product.web;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.invoice.category.service.CategoryService;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.product.entity.ProductQueryModel;
import cn.com.zv2.invoice.product.service.ProductService;
import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.invoice.supplier.service.SupplierService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class ProductAction extends BaseAction {

    public Long supplierId;
    public Long categoryId;
    public Product product = new Product();
    public ProductQueryModel productQueryModel = new ProductQueryModel();
    private ProductService productService;
    private SupplierService supplierService;
    private CategoryService categoryService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String list() {
        List<Supplier> supplierList = supplierService.list();
        put("supplierList", supplierList);
        // 使用别名解决关联查询报字段名不存在问题
//        List<Category> categoryList = categoryService.listBySupplier(supplierId);
//        productQueryModel.setCategoryList(categoryList);
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        Category category = new Category();
        category.setSupplier(supplier);
        productQueryModel.setCategory(category);
        setTotalRow(productService.count(productQueryModel));
        List<Product> productList = productService.list(productQueryModel, pageNum, pageSize);
        put("productList", productList);
        return LIST;
    }

    public String edit() {
        // 筛选出含有商品类别的供应商列表
        List<Supplier> supplierList = supplierService.listUnionCategory();
        put("supplierList", supplierList);
        // 加载第一个供应商对应的商品类别列表
        supplierId = supplierList.get(0).getId();
        if (product.getId() != null) {
            product = productService.get(product.getId());
            categoryId = product.getCategory().getId();
            supplierId = product.getCategory().getSupplier().getId();
        }
        List<Category> categoryList = categoryService.listBySupplier(supplierId);
        put("categoryList", categoryList);
        return EDIT;
    }

    public String updateIfPresent() {
        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);
        if (product.getId() == null) {
            productService.save(product);
        } else {
            productService.update(product);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        productService.delete(product);
        return REDIRECT_LIST;
    }

}
