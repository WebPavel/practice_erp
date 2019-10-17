package cn.com.zv2.invoice.order.web;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.invoice.category.service.CategoryService;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.order.entity.OrderQueryModel;
import cn.com.zv2.invoice.order.service.OrderService;
import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.invoice.product.service.ProductService;
import cn.com.zv2.invoice.supplier.entity.Supplier;
import cn.com.zv2.invoice.supplier.service.SupplierService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class OrderAction extends BaseAction {

    public Long supplierId;
    public Long categoryId;
    public Long productId;
    private Product product;
    public Order order = new Order();
    public OrderQueryModel orderQueryModel = new OrderQueryModel();
    private List<Category> categoryList;
    private List<Product> productList;

    private OrderService orderService;
    private SupplierService supplierService;
    private CategoryService categoryService;
    private ProductService productService;

    public Product getProduct() {
        return product;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public String list() {
        setTotalRow(orderService.count(orderQueryModel));
        List<Order> orderList = orderService.list(orderQueryModel, pageNum, pageSize);
        put("orderList", orderList);
        return LIST;
    }

    public String edit() {
        if (order.getId() != null) {
            order = orderService.get(order.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        if (order.getId() == null) {
            orderService.save(order);
        } else {
            orderService.update(order);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        orderService.delete(order);
        return REDIRECT_LIST;
    }

    public String buyList() {
        return "buyList";
    }

    public String buyEdit() {
        // 过滤有商品类别和商品的供应商列表信息
        List<Supplier> supplierList = supplierService.listUnionCategoryAndProduct();
        // 第一个供应商的类别
        List<Category> categoryList = categoryService.listNonNullProductBySupplier(supplierList.get(0).getId());
        // 第一个类别的商品
        List<Product> productList = productService.listByCategory(categoryList.get(0).getId());
        put("supplierList", supplierList);
        put("categoryList", categoryList);
        put("productList", productList);
        return "buyEdit";
    }

    public String sellList() {
        return "sellList";
    }

    public String sellEdit() {
        return "sellEdit";
    }

    // ============AJAX============
    public String ajaxListCategoryAndProduct() {
        // 过滤含有商品的类别列表信息
        categoryList = categoryService.listNonNullProductBySupplier(supplierId);
        productList = productService.listByCategory(categoryList.get(0).getId());
        product = productList.get(0);
        return "ajaxListCategoryAndProduct";
    }

    public String ajaxListProductByCategory() {
        productList = productService.listByCategory(categoryId);
        product = productList.get(0);
        return "ajaxListProductByCategory";
    }

    public String ajaxGetProductPrice() {
        product = productService.get(productId);
        return "ajaxGetProductPrice";
    }

}
