package cn.com.zv2.invoice.order.web;

import cn.com.zv2.auth.employee.entity.Employee;
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

import java.util.Arrays;
import java.util.List;

public class OrderAction extends BaseAction {

    public Long supplierId;
    public Long categoryId;
    public Long productId;
    public String usedProductIds;
    public Long[] productIds;
    public Integer[] quantities;
    public Double[] prices;
    public String applicantName;
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
        Employee applicant = new Employee();
        applicant.setName(applicantName);
        orderQueryModel.setApplicant(applicant);
        setTotalRow(orderService.count(orderQueryModel));
        List<Order> orderList = orderService.listBuyOrder(orderQueryModel, pageNum, pageSize);
        put("orderList", orderList);
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

    public String buySave() {
        System.out.println(supplierId);
        System.out.println(Arrays.asList(productIds));
        System.out.println(Arrays.asList(quantities));
        System.out.println(Arrays.asList(prices));
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        order.setSupplier(supplier);
        orderService.saveBuyOrder(order, productIds, quantities, prices, getSessionEmployee());
        return "buySave";
    }

    public String buyDetail() {
        order = orderService.get(order.getId());
        return "buyDetail";
    }

    public String buyAuditList() {
        Employee applicant = new Employee();
        applicant.setName(applicantName);
        orderQueryModel.setApplicant(applicant);
        setTotalRow(orderService.countBuyAudit(orderQueryModel));
        List<Order> orderList = orderService.listBuyAudit(orderQueryModel, pageNum, pageSize);
        put("orderList", orderList);
        return "buyAuditList";
    }

    public String buyDetailAudit() {
        order = orderService.get(order.getId());
        return "buyDetailAudit";
    }

    public String buyAuditApprove() {
        orderService.buyAuditApprove(order.getId(), getSessionEmployee());
        return "buyAuditApprove";
    }

    public String buyAuditReject() {
        orderService.buyAuditReject(order.getId(), getSessionEmployee());
        return "buyAuditReject";
    }

    // ============AJAX============
    public String ajaxListCategoryAndProduct() {
        // 过滤含有商品的类别列表信息
        categoryList = categoryService.listNonNullProductBySupplier(supplierId);
        productList = productService.listByCategory(categoryList.get(0).getId());
        if (usedProductIds != null) {
            for (int i = productList.size() - 1; i >= 0; i--) {
                Long productId = productList.get(i).getId();
                if (usedProductIds.contains("'" + productId + "'")) {
                    productList.remove(i);
                }
            }
        }
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

    public String ajaxListNewCategoryAndProduct() {
        // 过滤含有商品的类别列表信息
        // 添加商品前判断商品是否已出现在已存在商品列表里,如果存在剔除,
        // 如果一个类别中所有商品都剔除，就将该商品类别也剔除
        categoryList = categoryService.listNonNullProductBySupplier(supplierId);
        Product:
        for (int i = categoryList.size() - 1; i >= 0; i--) {
            List<Product> productList = productService.listByCategory(categoryList.get(i).getId());
            for (Product product : productList) {
                if (!usedProductIds.contains("'" + product.getId() + "'")) {
                    continue Product;
                }
            }
            categoryList.remove(i);
        }
        productList = productService.listByCategory(categoryList.get(0).getId());
        for (int i = productList.size() - 1; i >= 0; i--) {
            Long productId = productList.get(i).getId();
            if (usedProductIds.contains("'" + productId + "'")) {
                productList.remove(i);
            }
        }
        product = productList.get(0);
        return "ajaxListNewCategoryAndProduct";
    }

    private Employee getSessionEmployee() {
        return (Employee) getSession(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME);
    }

}
